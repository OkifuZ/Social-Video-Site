<template>
  <base-section id="videos-row">
    <base-section-heading 
    :title="'你来注册啦'">
      <div>
        <p>不过注册无奖唷</p>
      </div>
    </base-section-heading>
  <v-app>
    <v-card
        width="400"
        class="mx-auto mt-5"
    >
      <v-card-title>
        <h1 class="display-2">Register</h1>
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
          <v-text-field
            label="昵称"
            v-model="nickname"
            prepend-icon="mdi-account-circle"
          />
        </v-form>
      </v-card-text>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn
            color="success"
            @click="doRegisterAction"
        >
            Register
        </v-btn>
        
      </v-card-actions>
    </v-card>
  </v-app>
  </base-section>
</template>

<script>
  // import common from '@/common.vue'
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

    name: 'SectionRegister',

    components: {},

    data: () => ({
      password: '',
      username: '',
      nickname: '',
      showPassword: true,
    }),

    methods: {
      doRegisterAction: function () {
        this.$axios
          .post('/register', {
            username: this.username,
            password: this.password,
            nickname: this.nickname,
          })
          .then(successResponse => {
            if (successResponse.data.code === 200) {
              this.$router.replace({ path: 'my-home' })
            } else {
              alert('register result failed, code: ' + successResponse.data.code + ' ' + successResponse.data.content)
            }
          })
          .catch(failResponse => {
            alert('register response failed')
          })
      },
    },
  }
</script>

<style>

</style>
