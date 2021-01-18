<template>
  <div>
    <base-section id="theme-features"
    >
    <base-section-heading 
      :title="this.$cookies.get('nickname') + ' 与 ' + this.userNickname + ' 的对话'"
    >
      一定要友好交流唷
    </base-section-heading>
    <v-card
      align="center"
      justify="center"
      max-width="1400"
      class="mx-auto my-12"
    >
    <v-toolbar
            flat
            color="primary"
            dark
          >
            <v-toolbar-title>聊天室</v-toolbar-title>
          </v-toolbar>
    <v-container
      id="scroll-target"
      style="max-height: 400px"
      class="overflow-y-auto"
    >
      
        
          
          <base-chat-box
                v-for="(box, i) in charBoxes"
                :key="i"
                :from="box.fromNum"
                :message="box.message"
                >
          </base-chat-box>
        </v-container>
          <v-card-text>
            <v-textarea
              filled
              v-model="content"
              label="Text"
              :value="content"
            ></v-textarea>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="success"
              depressed
              @click="sendMessage()"
            >
              Post
            </v-btn>
          </v-card-actions>
    </v-card>
  </base-section>
  </div>
</template>

<script>
  export default {
    name: 'SectionContactUs',
    created() {
      this.getCharBox()
      this.getUserInfo()
    },

    data: () => ({
      myname: 'Kirara',
      userNickname: '',
      content: '',
      charBoxes: null
    }),
    methods: {
      getCharBox: function() {
        this.$axios
        .get('/messages/with='+this.$route.params.targetUser, 
          {headers : {token: this.$cookies.get("token")}}
        )
        .then(res => {
            // alert(this.$cookies.get("token"))
            this.charBoxes = res.data
        })
        .catch(failResponse => {
            alert('char get response failed')
        })
      },
      sendMessage: function() {
         this.$axios
        .post('/messages/send', {
          targetUserId: this.$route.params.targetUser,
          messages: this.content
        }, {headers: {token: this.$cookies.get("token")}})
        .then(res => {
            // alert(this.$route.params.userID)
            if (res.data.code == 200) {
              this.$router.go(0)
            }
            else {
              alert('send failed 400')
            }
        })
        .catch(failResponse => {
          alert('post get response failed')
        })
        
      },
      getUserInfo: function() {
        this.$axios
        .get('/userId='+this.$route.params.targetUser + '/info', 
         {headers : {token: this.$cookies.get("token")}})
        .then(res => {
          this.userNickname = res.data.data.nickname
        })
        .catch(failResponse => {
            alert('info get response failed')
        })
      },
    },

  }

</script>
