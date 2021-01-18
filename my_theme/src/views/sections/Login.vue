<template>
  <base-section id="videos-row">
    <base-section-heading 
    :title="'想一览无余？Login吧少年'">
      <div>
        <p>如果没有账号，点击Register去注册哟</p>
      </div>
    </base-section-heading>
  <v-app>
    <v-card
        width="400"
        class="mx-auto mt-5"
    >
      <v-card-title>
        <h1 class="display-2">Login</h1>
      </v-card-title>
      <v-card-text>
        <v-form>
          <v-text-field
            label="Username"
            v-model="username"
            prepend-icon="mdi-account-circle"
          />
          <v-text-field
            :type="showPassword ? 'text' : 'password'"
            label="Password"
            v-model="password"
            prepend-icon="mdi-lock"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            @click:append="showPassword = !showPassword"
          />
        </v-form>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn
            color="success"
            @click="gotoRegister"
        >
            Register
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
            color="info"
            @click="doLoginAction"
        >
            Login
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-app>
  </base-section>
</template>

<script>
  import common from '@/common.vue'
  export default {
    theme: {
      themes: {
        light: {
          primary: '#3f51b5',
          secondary: '#b0bec5',
          accent: '#8c9eff',
          error: '#b71c1c',
        },
      },
    },

    name: 'SectionLogin',

    components: {},

    data: () => ({
      password: '',
      username: '',
      showPassword: true,
    }),

    methods: {
      doLoginAction: function () {
        this.$axios
          .post('/login', {
            username: this.username,
            password: this.password,
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$cookies.set('token', successResponse.data.data.token)
              this.$cookies.set('isAdmin', successResponse.data.data.isAdmin)
              this.$cookies.set('userId', successResponse.data.data.userId)
              this.$cookies.set('nickname', successResponse.data.data.nickname)
              this.$cookies.set('isLogin', true)
              
              // common.setUserId(successResponse.data.data.userId)
              // common.doLogin(this.username, this.password, successResponse.data.data.nickname, successResponse.data.data.token)
              this.$router.replace({ path: 'my-home' })
            } else {
              alert('login result failed, code: ' + successResponse.data.code + ' ' + successResponse.data.content)
            }
          })
          .catch(failResponse => {
            alert('login response failed')
          })
      },
      gotoRegister: function() {
        this.$router.push({ path: 'register' })
      },
      
    },
  }
</script>

<style>

</style>
