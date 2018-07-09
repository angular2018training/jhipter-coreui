export const navItems = [
  {
    name: 'Dashboard',
    url: '/dashboard',
    icon: 'icon-speedometer',
    badge: {
      variant: 'info',
      text: 'NEW'
    }
  },
  {
    name: 'Administration',
    url: '/admin',
    icon: 'icon-cursor',
    children: [
      {
        name: 'User management',
        url: '/admin/user-management',
        icon: 'icon-cursor'
      },
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
      },
      {
        name: 'User group',
        url: '/admin/user-group',
        icon: 'icon-cursor'
      },



    ]
  },
  {
    name: 'Set up',
    url: '/setup',
    icon: 'icon-cursor',
    children: [
      {
        name: 'Post Office',
        url: '/setup/post-office',
        icon: 'icon-cursor'
      },
      {
        name: 'Province',
        url: '/setup/province',
        icon: 'icon-cursor'
      },
      {
        name: 'Order Services',
        url: '/setup/order-services',
        icon: 'icon-cursor'
      },
      {
        name: 'Order Services Type',
        url: '/setup/order-services-type',
        icon: 'icon-cursor'
      },

      {
        name: 'Quotation',
        url: '/setup/quotation',
        icon: 'icon-cursor'
      },

      {
        name: 'Bank',
        url: '/setup/bank',
        icon: 'icon-cursor'
      },
      {
        name: 'Payment type',
        url: '/setup/payment-type',
        icon: 'icon-cursor'
      },

    ]
  },
  {
    name: 'Customer Management',
    url: '/customer-management',
    icon: 'icon-cursor',
    children: [
      {
        name: 'Customer',
        url: '/customer-management',
        icon: 'icon-cursor'
      }
    ]
  }


];
