export const navItems = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer'
  },
  {
    name: 'Hệ thống',
    url: '/admin',
    icon: 'fa fa-gears',
    children: [
      {
        name: 'Người dùng',
        url: '/admin/user-management',
        icon: 'icon-user'
      },
      {
        name: 'Nhóm người dùng',
        url: '/admin/user-group',
        icon: 'fa fa-group'
      }
      /*,
      {
        name: 'Metrics',
        url: '/admin/jhi-metrics',
        icon: 'icon-cursor'
      },
      {
        name: 'Health',
        url: '/admin/jhi-health',
        icon: 'icon-cursor'
      },
      {
        name: 'Configuration',
        url: '/admin/jhi-configuration',
        icon: 'icon-cursor'
      },
      {
        name: 'Audits',
        url: '/admin/audits',
        icon: 'icon-cursor'
      },
      {
        name: 'Logs',
        url: '/admin/logs',
        icon: 'icon-cursor'
      },
      {
        name: 'API',
        url: '/admin/docs',
        icon: 'icon-cursor'
      },
      {
        name: 'Elastic search Reindex',
        url: '/admin/elasticsearch-reindex',
        icon: 'icon-cursor'
      }*/
    ]
  },
  {
    name: 'Danh mục',
    url: '/setup',
    icon: 'fa fa-list-alt',
    children: [
      {
        name: 'Bưu cục',
        url: '/setup/post-office',
        icon: 'fa fa-check'
      },
      {
        name: 'Tỉnh / Thành phố',
        url: '/setup/province',
        icon: 'fa fa-check'
      },
      {
        name: 'Quận / Huyện',
        url: '/setup/district',
        icon: 'fa fa-check'
      },
      {
        name: 'Dịch vụ',
        url: '/setup/order-services',
        icon: 'fa fa-check'
      },
      {
        name: 'Loại dịch vụ',
        url: '/setup/order-services-type',
        icon: 'fa fa-check'
      },
      {
        name: 'Dịch vụ cộng thêm',
        url: '/setup/order-sub-services',
        icon: 'fa fa-check'
      },
      {
        name: 'Phương thức tính',
        url: '/setup/order-sub-services-type',
        icon: 'fa fa-check'
      },
      {
        name: 'Trạng thái',
        url: '/setup/order-status',
        icon: 'fa fa-check'
      },
      {
        name: 'Ngân hàng',
        url: '/setup/bank',
        icon: 'fa fa-check'
      },
      {
        name: 'Lịch thanh toán',
        url: '/setup/payment-type',
        icon: 'fa fa-check'
      },

    ]
  },
  {
    name: 'Khách hàng',
    url: '/customer-management',
    icon: 'fa fa-shopping-basket',
    children: [
      {
        name: 'Khách hàng',
        url: '/customer-management',
        icon: 'fa fa-user-circle-o'
      }
    ]
  }


];
