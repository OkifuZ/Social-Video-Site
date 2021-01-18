<template>
  <v-menu
    v-model="menu"
    :close-on-content-click="true"
    bottom
    left
    max-width="90%"
    min-width="275"
    offset-x
    offset-y
    origin="top right"
    transition="scale-transition"
  >
    <template v-slot:activator="{ attrs, on }">
      <v-card
        id="settings"
        class="py-2 px-4"
        color="#0000002D"
        dark
        flat
        style="position: fixed; top: 164px; right: -35px;"
        width="100"
        v-bind="attrs"
        v-on="on"
      >
        <v-icon
          large
          color="blue lighten-1"
        >
          mdi-account
        </v-icon>
      </v-card>
    </template>
    <v-card class="py-2">

      <v-card-text>
        <base-btn
          v-if="isLogin() && ! isAdmin()"
          block
          :to="'/personal-inf/' + getUserId()"
          class="mb-6"
          color="blue lighten-1"
          dark
        >
          Hi, {{getName()}}
        </base-btn>
        <base-btn
          v-if="(isLogin() && isAdmin())"
          block
          :to="'/personal-inf/' + getUserId()"
          class="mb-6"
          color="red lighten-1"
          dark
        >
          Hi, 管理员
        </base-btn>
        <base-btn
          v-if="isLogin()"
          block
          :to="'/all-messages/' + getUserId()"
          class="mb-6"
          color="green"
          dark
        >
          我的私信
        </base-btn>
        <base-btn
          v-if="isLogin()"
          block
          class="mb-6"
          color="grey darken-3"
          dark
          @click="doLogoutAction"
        >
          注销登录
        </base-btn>
        <base-btn
          v-if="!isLogin()"
          block
          :to="'/login'"
          class="mb-6"
          color="accent"
        >
          请登录
        </base-btn>
        <base-btn
          v-if="!isLogin()"
          block
          :to="'/register'"
          class="mb-6"
          color="green darken-2"
          dark
        >
          去注册
        </base-btn>

        <v-divider class="my-6" />

        <base-title
          align="center"
          title="切换主题"
          space="0"
        />
        <v-item-group
          v-model="$vuetify.theme.themes.light.primary"
          class="d-flex justify-center"
        >
          <v-item
            v-for="color in colors"
            :key="color"
            :value="color"
          >
            <template v-slot="{ active, toggle }">
              <base-avatar
                :color="color"
                :outlined="active"
                class="ma-2"
                size="48"
                style="cursor: pointer;"
                @click.stop="toggle"
              />
            </template>
          </v-item>
        </v-item-group>

        <v-divider class="my-6" />

        <div class="text-center">
          <base-btn
            :tile="false"
            :to="{ name: 'Pro' }"
            rounded
            @click="menu = false"
          >
            Support Us

            <v-icon right>
              mdi-heart-plus-outline
            </v-icon>
          </base-btn>
        </div>
      </v-card-text>
    </v-card>
  </v-menu>
</template>

<script>
  // import common from '@/common.vue'
  export default {
    name: 'HomeSettings',
    
    data () {
      return {
        colors: [
          this.$vuetify.theme.themes.light.primary,
          '#9368e9',
          '#F4511E',
          '#FFA500',
        ],
        menu: false,
      }
    },
    methods: {
      getUserId: function() {
        return this.$cookies.get("userId")
      },
      doLogoutAction: function () {
        // common.doLogout()
        this.$cookies.set("token", '')
        this.$cookies.set("userId", '')
        this.$cookies.set("nickname", '')
        this.$cookies.set("isAdmin", false)
        this.$cookies.set('isLogin', false)
        if (this.$route.path.includes('my-home')) {
          this.$router.go(0)
        }
        else 
          this.$router.push({ path: '/my-home' })
      },
      isLogin: function () {
        return (this.$cookies.get("isLogin") === 'true')
        // return common.isLogin()
      },
      isAdmin: function(){
          return (this.$cookies.get("isAdmin") === 'true')
      },
      getName : function(){
        if (this.$cookies.isKey("nickname"))
          return this.$cookies.get("nickname")
        else 
          return ''
      },
      
    },
  }
</script>
