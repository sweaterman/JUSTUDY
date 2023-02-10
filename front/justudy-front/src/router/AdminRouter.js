import AdminMain from '../views/Admin/AdminMain.vue';
import AdminUser from '../views/Admin/AdminUser.vue';
import AdminUserUpdate from '../views/Admin/AdminUserUpdate.vue';
import AdminStudy from '../views/Admin/AdminStudy.vue';
import AdminStudyUpdate from '../views/Admin/AdminStudyUpdate.vue';
import AdminCommunity from '../views/Admin/AdminCommunity.vue';
import AdminCommunityUpdate from '../views/Admin/AdminCommunityUpdate.vue';
import AdminReport from '../views/Admin/AdminReport.vue';

export default [
    {
        path: '/admin',
        name: 'AdminMain',
        component: AdminMain
    },
    {
        path: '/admin/user/:idx',
        name: 'AdminUser',
        component: AdminUser
    },
    {
        path: 'admin/user/:idx/update',
        name: 'AdminUserUpdate',
        component: AdminUserUpdate
    },
    {
        path: '/admin/study/:idx',
        name: 'AdminStudy',
        component: AdminStudy
    },
    {
        path: 'admin/study/:idx/update',
        name: 'AdminStudyUpdate',
        component: AdminStudyUpdate
    },
    {
        path: '/admin/community/:idx',
        name: 'AdminCommunity',
        component: AdminCommunity
    },
    {
        path: 'admin/community/:idx/update',
        name: 'AdminCommunityUpdate',
        component: AdminCommunityUpdate
    },
    {
        path: '/admin/report/:idx',
        name: 'AdminReport',
        component: AdminReport
    },
];
