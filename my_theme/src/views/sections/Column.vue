<template>
  <div>
    <base-section id="theme-features"
    >
    <base-section-heading 
      :title="'专栏: ' + columnTitle"
    >
        <p>发布时间: {{time}}</p>
        
        <p>作者： <router-link to="/personal-inf">{{userNickname}}</router-link></p>
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
        mdi-message
      </v-icon>
      <span class="title font-weight-light">column</span>
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
        <v-btn text
        @click="addColumnToLike()"
        >
          <v-icon class="mr-1">
            mdi-thumb-up
          </v-icon>
        </v-btn>
        
        <v-dialog
                v-model="dialog"
                width="500"
                >
                <template v-slot:activator="{ on, attrs }">
                    
                    <v-btn text
                    dark
                    v-bind="attrs"
                    v-on="on"
                    >
                      <v-icon class="mr-1">
                        mdi-heart
                      </v-icon>
                    </v-btn>
                    <!-- <v-btn
                    color="red lighten-2"
                    dark
                    v-bind="attrs"
                    v-on="on"
                    >
                    Click Me
                    </v-btn> -->
                </template>

                <v-card>
                    <v-card-title class="headline grey lighten-2">
                    请填写收藏夹的名字~
                    </v-card-title>

                    <v-divider></v-divider>
                    <v-card flat
                    max-width="400"
                    class="mx-auto">
                    <v-text-field
                    v-model="favName"
                    label="Name"
                    ></v-text-field>
                    </v-card>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                        color="primary"
                        text
                        @click="addBangumiToFav()"
                    >
                        I accept
                    </v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
          
        </v-row>
      </v-list-item>
    </v-card-actions>
  </v-card>
     <v-row justify="space-around" >
                
                <v-card
                    flat
                    max-width="1200"
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
            <strong class="title">以{{this.$cookies.get('nickname')}}的名义发表对此专栏的评论:</strong>
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
      this.getColumnInfo()
    },
    data: () => ({
        time: '',
        userNickname: '',
        columnTitle: '',
        favName: '',
        dialog: false,
        userId:'',
      content: '努力加载中',
      charBoxes: null,
      
      tags: null
    }),
    methods: {
        addBangumiToFav() {
          this.dialog=false
          this.$axios
          .post('favorite/column',{
              favoriteId: this.$route.params.columnId,
              favoriteName: this.favName
          }, 
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
              alert('fav BANGUMI response failed')
          })
        },
        sendComment: function () {
            this.charBoxes.push({
                name: 'Trump',
                message: this.comment,
            })
            this.comment = ''
        },
        addColumnToLike() {
            this.$axios
            .post('like/columnId='+this.$route.params.columnId, {}, 
              {headers: {token: this.$cookies.get("token")}})
            .then(res => {
                if (res.data.code === 200) {
                // this.$router.go(0)
                // this.$router.replace({ path: 'my-home' })
                } else {
                    alert(res.data.msg)
                }
            })
            .catch(failResponse => {
                alert('LIKE column response failed')
            })
        },
        gotoTag: function(tagname) {
          this.$router.push({ path: '/tag-page/'+tagname })
        },
        sendComment: function () {
          this.$axios
          .post('comments/post/column', {
            content: this.comment,
            targetId: this.$route.params.columnId
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
        getColumnInfo: function() {
          this.$axios
          .get('/columns/id='+this.$route.params.columnId,
          {headers: {token: this.$cookies.get("token")}})
          .then(res => {
              if (res.data.code === 200) {
                this.userId=res.data.data.userId
                  this.tags = res.data.data.tags
                  this.charBoxes = res.data.data.comments
                  this.time = res.data.data.columnUploadDate
                  this.columnTitle = res.data.data.columnName
                  this.content = res.data.data.columnAbstract
                  this.userNickname = res.data.data.nickname
              } else {
                  alert(res.data.msg)
              }
          })
          .catch(failResponse => {
              alert('column get response failed')
          })
        }
      },

  }

</script>
