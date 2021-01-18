<template>
  <base-section id="messages">
    <base-section-heading title="收到的全部私信唷">
        有什么好消息呀
    </base-section-heading>
    <v-container>
        <v-col>
            <v-window
                v-model="window"
                vertical
                v-for="(mes ,i) in messages"
                :key="i"
            >
                <v-window-item>
                <v-card flat>
                    <v-card-text>
                    <v-row
                        class="mb-4"
                        align="center"
                    >
                        <v-icon>mdi-account</v-icon>
                        <v-btn 
                            text
                            @click="goto('/personal-inf/' + mes.withWhomId)"
                        >
                        <strong>{{mes.withWhomNickname}}</strong>
                        </v-btn>
                            给你发来了私信
                        <v-spacer></v-spacer>
                        <v-btn
                            text
                            @click="goto('/chatting/' + mes.withWhomId)"
                        >
                            回复
                        </v-btn>
                    </v-row>

                    <p>
                        {{mes.latestMessage.message}}
                    </p>
                    At {{mes.latestMessage.sendTime}}
                    </v-card-text>
                </v-card>
                </v-window-item>
                <v-divider />
            </v-window>
        </v-col>
    </v-container>
  </base-section>

</template>

<script>
  export default {
    name: 'SectionMessages',
    created() {
        this.getAllMessages()
    },
    data: () => ({
        messages: null,
    }),
    methods: {
        getAllMessages: function() {
            this.$axios
            .get('/messages/all', 
                {headers : {token: this.$cookies.get("token")}} 
            )
            .then(res => {
                this.messages = res.data
            })
            .catch(failResponse => {
                alert('messages get response failed')
            })
        },
      goto: function (des) {
        this.$router.push({ path: des })
      },
      isLogin: function () {
        return true
        // return common.isLogin()
      },
    },
  }
</script>
