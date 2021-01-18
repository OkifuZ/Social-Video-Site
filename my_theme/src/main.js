import Vue from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import './plugins'
import {videoPlayer} from 'vue-video-player'
import VueCookies from 'vue-cookies'
require('video.js/dist/video-js.css')
require('vue-video-player/src/custom-theme.css')
Vue.config.productionTip = false
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8097/api'
Vue.prototype.$axios = axios
Vue.use(videoPlayer)
Vue.use(VueCookies)

new Vue({
  router,
  vuetify,
  videoPlayer,
  VueCookies,
  render: h => h(App),
}).$mount('#app')
