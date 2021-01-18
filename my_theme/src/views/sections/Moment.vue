<template>
  <div>
    <base-section id="theme-features"
    >
    <base-section-heading 
      :title="userNickname + ' 的动态内容'"
    >
        发布时间: {{time}}
    </base-section-heading>
    <v-card
      align="center"
      justify="center"
      max-width="1400"
      flat
      class="mx-auto my-12"
    >
    
    
        <v-card
    class="mx-auto"
    color="primary"
    flat
    dark
    max-width="1300"
  >
    <v-card-title>
      <v-icon
        large
        left
      >
        mdi-twitter
      </v-icon>
      <span class="title font-weight-light">moment</span>
    </v-card-title>

    <v-card-text class="headline font-weight-bold">
      {{content}}
    </v-card-text>

    <v-card-actions>
      <v-list-item class="grow">
        <v-list-item-avatar color="primary">
          <v-icon>
              mdi-account
          </v-icon>
        </v-list-item-avatar>
        <v-btn text :to="'/personal-inf/'+this.userId">
            {{userNickname}}
        </v-btn>
        <v-row
          align="center"
          justify="end"
        >
          <span class="mr-1"></span>
          <v-btn text
          v-if="!this.liked"
          @click="addMomentToLike()"
          >
          <v-icon 
          >
            mdi-heart
          </v-icon>
          </v-btn>
          <v-btn text
          v-if="this.liked"
          >
          <v-icon 
          >
            mdi-heart
          </v-icon>
          </v-btn>
          
        </v-row>
      </v-list-item>
    </v-card-actions>
  </v-card>
     <v-row justify="space-around" >
                
                <v-card
                    flat
                    max-width="800"
                >
                    <v-chip-group
                        multiple
                        active-class="primary--text"
                        flat
                    >
                    <v-chip
                        v-for="(tag, i) in tags"
                        :key="i"
                        color="primary"
                        dark
                        @click="gotoTag(tag.id)"
                    >
                        tag{{i+1}}: {{ tag.tagName }}
                    </v-chip>
                    </v-chip-group>
                </v-card>
           
         </v-row>
          <v-card flat max-width="1300">
            <v-col>
                <v-window
                    v-model="window"
                    vertical
                    v-for="(com ,i) in charBoxes"
                    :key="i"
                >
                    <v-window-item>
                    <v-card flat>
                        <v-card-text>
                        <v-row
                            class="mb-4"
                            align="center"
                        >
                            <v-btn text>
                            <strong>{{com.commentSenderNickname}}</strong>
                            </v-btn>
                            发表了评论
                            <v-spacer></v-spacer>
                            <v-btn icon>
                            <v-icon color="primary">mdi-account</v-icon>
                            </v-btn>
                        </v-row>

                        <p>
                            {{com.commentContent}}
                        </p>

                        </v-card-text>
                    </v-card>
                    </v-window-item>
                    <v-divider />
                </v-window>
            </v-col>
            <strong class="title">以{{this.$cookies.get('nickname')}}的名义发表对这条动态的评论:</strong>
          <v-card-text>
            <v-textarea
              filled
              v-model="comment"
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
              @click="sendComment()"
            >
              Post
            </v-btn>
          </v-card-actions>
          </v-card>
    </v-card>
  </base-section>
  </div>
</template>

<script>
  export default {
    name: 'SectionContactUs',
    created() {
      this.getMomentInfo()
    },

    data: () => ({
        time: '2077-5-17 5:35',
        userNickname: '',
        userId: null,
      comment: '',
      liked: false,
      content: '努力加载内容中',
      charBoxes: null,
      tags: null,
    }),
    methods: {
          addMomentToLike() {
            this.$axios
            .post('like/dynamicId='+this.$route.params.momentId, {}, 
              {headers: {token: this.$cookies.get("token")}})
            .then(res => {
                if (res.data.code === 200) {
                this.$router.go(0)
                // this.$router.replace({ path: 'my-home' })
                } else {
                    alert(res.data.msg)
                }
            })
            .catch(failResponse => {
                alert('LIKE moment response failed')
            })
        },
        getMomentInfo: function() {
          this.$axios
          .get('/dynamics/id='+this.$route.params.momentId,
          {headers: {token: this.$cookies.get("token")}})
          .then(res => {
              if (res.data.code === 200) {
                  this.time = res.data.data.sendTime
                  this.content = res.data.data.dynamicContent
                  this.charBoxes = res.data.data.comments
                  this.liked = res.data.data.likedByCurrentWatcher
                  this.tags = res.data.data.tags
                  this.userId = res.data.data.userId
                  this.userNickname = res.data.data.userNickname
                  // this.tags = res.data.data.tags
                  // this.charBoxes = res.data.data.comments
                  // this.time = res.data.data.columnUploadDate
                  // this.columnTitle = res.data.data.columnName
                  // this.content = res.data.data.columnAbstract
                  // this.userNickname = res.data.data.nickname
              } else {
                  alert(res.data.msg)
              }
          })
          .catch(failResponse => {
              alert('moment get response failed')
          })
        },
        sendComment: function () {
          this.$axios
          .post('comments/post/dynamic', {
            content: this.comment,
            targetId: this.$route.params.momentId
          }, {headers: {token: this.$cookies.get("token")}})
          .then(res => {
            if (res.data.code === 200) {
              this.$router.go(0)
              // this.$router.replace({ path: 'my-home' })
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('post comment response failed')
          })
        },
        gotoTag: function(tagname) {
            alert(tagname)
            this.$router.push({ path: '/tag-page/'+tagname })
        },
      },
      
      

  }

</script>
