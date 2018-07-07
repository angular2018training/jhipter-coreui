package vn.nextlogix.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Weight entity. This class is used in WeightResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /weights?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class WeightCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter minAmount;

    private DoubleFilter maxAmount;

    private StringFilter name;

    private StringFilter description;

    public WeightCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(DoubleFilter minAmount) {
        this.minAmount = minAmount;
    }

    public DoubleFilter getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(DoubleFilter maxAmount) {
        this.maxAmount = maxAmount;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WeightCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (minAmount != null ? "minAmount=" + minAmount + ", " : "") +
                (maxAmount != null ? "maxAmount=" + maxAmount + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
            "}";
    }

}
