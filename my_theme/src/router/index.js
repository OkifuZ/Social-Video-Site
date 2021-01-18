// Imports
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  scrollBehavior: (to, from, savedPosition) => {
    if (to.hash) return { selector: to.hash }
    if (savedPosition) return savedPosition

    return { x: 0, y: 0 }
  },
  routes: [
    {
      path: '/',
      component: () => import('@/layouts/home/Index.vue'),
      children: [
        {
          path: '',
          name: 'Home',
          component: () => import('@/views/my-home/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'my-home',
          name: 'MyHome',
          component: () => import('@/views/my-home/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'all-messages/:userId',
          name: 'AllMessages',
          component: () => import('@/views/all-messages/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'tag-page/:tagId',
          name: 'TagPage',
          component: () => import('@/views/tag-page/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'column-page/:columnId',
          name: 'ColumnPage',
          component: () => import('@/views/column-page/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'chatting/:targetUser',
          name: 'Chatting',
          component: () => import('@/views/chatting/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'search-result/:search',
          name: 'SearchResult',
          component: () => import('@/views/search-result/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'video/:videoID',
          name: 'Video',
          component: () => import('@/views/video/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'videos',
          name: 'Videos',
          component: () => import('@/views/videos/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'special-column',
          name: 'SpecialColumn',
          component: () => import('@/views/special-column/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'favorite/:favId',
          name: 'Favorite',
          component: () => import('@/views/favorite/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'bangumi',
          name: 'Bangumi',
          component: () => import('@/views/bangumi/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'bangumi-play/:bangumiId',
          name: 'BangumiPlay',
          component: () => import('@/views/bangumi-play/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'columns',
          name: 'Columns',
          component: () => import('@/views/columns/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'login',
          name: 'Login',
          component: () => import('@/views/login/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'register',
          name: 'Register',
          component: () => import('@/views/register/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'personal-inf/:userID',
          name: 'PersonalInf',
          component: () => import('@/views/personal-inf/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'personal-inf/another/:userID',
          name: 'PersonalInf',
          component: () => import('@/views/personal-inf/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'selected-tag',
          name: 'SelectedTag',
          component: () => import('@/views/selected-tag/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'moment/:momentId',
          name: 'Moment',
          component: () => import('@/views/moment/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'favorite',
          name: 'Favorite',
          component: () => import('@/views/favorite/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'creation-center',
          name: 'CreationCenter',
          component: () => import('@/views/creation-center/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'chatting',
          name: 'About',
          component: () => import('@/views/chatting/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'about',
          name: 'About',
          component: () => import('@/views/about/Index.vue'),
          meta: { src: require('@/assets/welcome.jpg') },
        },
        {
          path: 'contact-us',
          name: 'Contact',
          component: () => import('@/views/contact-us/Index.vue'),
          meta: { src: require('@/assets/contact.jpg') },
        },
        {
          path: 'pro',
          name: 'Pro',
          component: () => import('@/views/pro/Index.vue'),
          meta: { src: require('@/assets/pro.jpg') },
        },
        {
          path: '*',
          name: 'FourOhFour',
          component: () => import('@/views/404/Index.vue'),
        },
      ],
    },

  ],
})

export default router
