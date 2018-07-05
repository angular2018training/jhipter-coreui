package vn.nextlogix.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.SetJoin;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.StringFilter;
import vn.nextlogix.config.Constants;
import vn.nextlogix.domain.Company_;
import vn.nextlogix.domain.PostOffice;
import vn.nextlogix.domain.PostOffice_;
import vn.nextlogix.domain.Quotation_;
import vn.nextlogix.domain.User;
import vn.nextlogix.domain.UserExtraInfo;
import vn.nextlogix.domain.UserExtraInfo_;
import vn.nextlogix.domain.UserGroup_;
import vn.nextlogix.domain.UserGroup;
import vn.nextlogix.domain.UserPostOffice;
import vn.nextlogix.domain.UserPostOffice_;
import vn.nextlogix.domain.User_;
import vn.nextlogix.repository.UserRepository;
import vn.nextlogix.service.dto.UserCriteria;
import vn.nextlogix.service.dto.UserDTO;
import vn.nextlogix.service.dto.UserGroupCriteria;
import vn.nextlogix.service.mapper.UserExtraInfoMapper;
import vn.nextlogix.service.mapper.UserMapper;
@Service
@Transactional(readOnly = true)
public class UserQueryService extends QueryService<User>{

    private final Logger log = LoggerFactory.getLogger(UserQueryService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserExtraInfoMapper userExtraInfoMapper;
    
    
    
    public UserQueryService(UserRepository userRepository, UserMapper userMapper,
			UserExtraInfoMapper userExtraInfoMapper) {
		super();
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userExtraInfoMapper = userExtraInfoMapper;
	}
    
	private Specifications<User> createSpecification(UserCriteria criteria) {
        Specifications<User> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogin(), User_.login));
            }
            if (criteria.getName()!= null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), User_.name));
            }
            if (criteria.getEmail()!= null ) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), User_.email));
            }
            if (criteria.getPhone()!= null ) {
                specification = specification.and((root, query, builder) ->  builder.like(root.get(User_.userExtraInfo).get(UserExtraInfo_.phone), criteria.getPhone().getContains()) );
            }
 
          
            if (criteria.getActivated()!= null && criteria.getActivated().getEquals()!=null) {
                specification = specification.and(buildSpecification(criteria.getActivated(), User_.activated));
            }
            
            if (criteria.getCreatedDate()!= null ) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), User_.createdDate));
            }
            
            if(criteria.getCompanyId() !=null) {
            	specification = specification.and( (root, query, builder) -> builder.equal(root.get(User_.userExtraInfo).get(UserExtraInfo_.company).get(Company_.id), criteria.getCompanyId().getEquals()));
            }
            if(criteria.getPostOfficeId() !=null) {
            	specification = specification.and( (root, query, builder) -> {
            		final Join<User,UserExtraInfo> userUserExtraInfo =  root.join(User_.userExtraInfo);
            		SetJoin<UserExtraInfo, UserPostOffice> userExtraInfoUserPostOffice = userUserExtraInfo.join(UserExtraInfo_.userPostOfficeDetailLists);
            		Join<UserPostOffice, PostOffice> userPostOfficePostOffice = userExtraInfoUserPostOffice.join(UserPostOffice_.postOffice);
            		return builder.equal(userPostOfficePostOffice.get(PostOffice_.id),criteria.getPostOfficeId().getEquals());
            	});
            }
            if(criteria.getUserGroupId() !=null) {
            	specification = specification.and( (root, query, builder) -> {
            		final Join<User,UserExtraInfo> userUserExtraInfo =  root.join(User_.userExtraInfo);
            		SetJoin<UserExtraInfo, UserPostOffice> userExtraInfoUserPostOffice = userUserExtraInfo.join(UserExtraInfo_.userPostOfficeDetailLists);
            		Join<UserPostOffice, UserGroup> userPostOfficeUserGroup = userExtraInfoUserPostOffice.join(UserPostOffice_.userGroups);
            		return builder.equal(userPostOfficeUserGroup.get(UserGroup_.id),criteria.getUserGroupId().getEquals());
            	});
            }
          
        }
        return specification;
    }
   @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(UserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
         Specifications<User> specification = createSpecification(criteria);
        specification = specification.and( (root, query, builder) -> builder.notEqual(root.get(User_.login),  Constants.ANONYMOUS_USER));
        final Page<User> result = userRepository.findAll(specification, page);
        return result.map(UserDTO::new);
    }

}
