<template>
  <base-section id="about-our-product">
    
    <base-section-heading
      v-if="isLogin()"
      color="blue lighten-2"
      icon="mdi-account"
      :title="this.userNickname + ' 的个人中心'"
    />
    <base-section-heading
      v-if="!isLogin()"
      color="grey lighten-2"
      icon="mdi-account"
      :title="'请先登录'"
    />
    <v-divider />
  <v-tabs vertical>
      <v-tab>
        <v-icon left>
          mdi-account
        </v-icon>
        Ta的动态
      </v-tab>
      <v-tab>
        <v-icon left>
          mdi-plus
        </v-icon>
        Ta的收藏夹
      </v-tab>
      
      <v-tab>
        <v-icon left>
          mdi-access-point
        </v-icon>
        给Ta发私信
      </v-tab>
  
    <v-tab-item>
      <v-container
        v-if="isLogin()"
    >
      <v-row>
        <v-tabs
            v-model="tab"
            background-color="transparent"
            color="basil"
            grow
        >
        <v-tab
            href="#tab-1"
        >
            Ta的动态
        </v-tab>
        <v-tab
            href="#tab-2"
        >
            Ta的追番
        </v-tab>
        <v-tab
            href="#tab-3"
        >
            Ta的视频
        </v-tab>
        <v-tab
            href="#tab-4"
        >
            Ta的专栏
        </v-tab>
        </v-tabs>

        <v-tabs-items
            v-model="tab"
            grow
        >
        <v-tab-item
            :value="'tab-1'"
        >
            <v-card
            flat
            >
                <v-container>
                    <v-row>
                        <v-col
                        v-for="(feature, i) in moments"
                        :key="i"
                        cols="12"
                        md="6"
                        >
                        <base-avatar-card
                            v-bind="feature"
                            :title="feature.userNickname + ' 发布的动态'"
                            :icon="feature.icon"
                            align="left"
                            horizontal
                        >
                            <div>
                                <p>{{feature.dynamicContent}}
                                </p>
                                <v-btn
                                    text
                                    :to="'/personal-inf/' + feature.userId"
                                >
                                    发布者: {{feature.userNickname}}
                                </v-btn>
                                <v-divider
                                class="mx-4"
                                vertical
                                ></v-divider>
                                <v-btn
                                    text
                                    :to="'/moment/' + feature.dynamicId"
                                >
                                    ckeck the moment
                                </v-btn>
                            </div>
                        </base-avatar-card>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card>
        </v-tab-item>
        <v-tab-item
            :value="'tab-2'"
        >
            <v-card
            flat
            >
              <v-row>
                <v-col
                  v-for="(feature, i) in bangumis"
                  :key="i"
                  cols="auto"
                  md="3"
                >
                  <v-card
                    :loading="loading"
                    class="mx-auto my-12"
                    max-width="500"
                    min-width="300"
                  >
                    <v-img
                      height="250"
                      :src="feature.bangumiCoverUrl"
                    ></v-img>
                    <v-card-title>
                      {{feature.bangumiName}}
                    </v-card-title>
                    <v-card-text>
                      <div><div class="">{{feature.bangumiDescription}}</div></div>
                    </v-card-text>
                    <v-btn-toggle
                      v-model="text"
                      tile
                      color="deep-purple accent-3"
                      group
                    >
                      <v-btn
                        value="left"
                        :to="'/bangumi-play/'+bangumiId"
                      >
                        观看
                      </v-btn>
                    </v-btn-toggle>
                  </v-card>
                </v-col>
              </v-row>
            </v-card>
        </v-tab-item>
        <v-tab-item
            :value="'tab-3'"
        >
            <v-card
            flat
            >
            <v-row>
        <v-col
          v-for="(feature, i) in videos"
          :key="i"
          cols="auto"
          md="3"
        >
          <v-card
            :loading="loading"
            class="mx-auto my-12"
            min-width="300"
            max-width="500"
          >
            <v-img
              height="250"
              :src="feature.videoImageUrl"
            ></v-img>
            <v-card-title>
              {{feature.videoName}}
            </v-card-title>
            <v-card-text>
              <div><div class="">{{feature.videoDescription}}</div></div>
            </v-card-text>
            <v-btn-toggle
              v-model="text"
              tile
              color="deep-purple accent-3"
              group
            >
              <v-btn
                value="left"
                :to="'/video/'+feature.videoId"
              >
                观看
              </v-btn>
              
            </v-btn-toggle>
          </v-card>
        </v-col>
      </v-row>
            </v-card>
        </v-tab-item>
        <v-tab-item
            :value="'tab-4'"
        >
            <v-card
            flat
            >
            <v-row>
        <v-col
          v-for="(feature, i) in columns"
          :key="i"
          cols="12"
          md="6"
        >
          <base-avatar-card
            v-bind="feature"
            align="left"
            :icon="feature.icon"
            :title="feature.columnName"
            horizontal
          >
            <div>
                <p>{{feature.columnAbstract}}
                </p>
              
                <v-btn
                    text
                    :to="'/column-page/'+feature.columnId"
                >
                    click to have a look
                </v-btn>
            </div>
          </base-avatar-card>
        </v-col>
      </v-row>
            </v-card>
        </v-tab-item>
        </v-tabs-items>
      </v-row>
    </v-container>
    </v-tab-item>

    <v-tab-item>
        <base-section id="videos-row">
            <base-section-heading title="Ta的收藏夹都在这里啦">
            </base-section-heading>
            <v-row>
                <v-col
                v-for="(fav, i) in favs"
                :key="i"
                cols="auto"
                md="3"
                >
                <v-card
                    :loading="loading"
                    class="mx-auto my-12"
                    max-width="400"
                >
                    <v-img
                    height="250"
                    :src="fav.coverUrl"
                    ></v-img>
                    <v-card-title>
                    {{fav.FavoriteName}}
                    </v-card-title>
                    <v-btn-toggle
                    color="deep-purple accent-3"
                    group
                    >
                    <v-btn
                        value="left"
                        :to="'/favorite/' + fav.FavoriteId"
                    >
                        打开
                    </v-btn>
                    </v-btn-toggle>
                </v-card>
                </v-col>
            </v-row>
        </base-section>
    </v-tab-item>

    
    <v-tab-item>
      <div>
        <base-section id="theme-features"
    v-if="(this.$cookies.get('userId') === this.$route.params.userID)"
    >
    <base-section-heading 
      :title="'给自己发私信，禁止~'"
    >
      寂寞如此吗，去看番吧
    </base-section-heading>
        </base-section>
    <base-section id="theme-features"
    v-if="!(this.$cookies.get('userId') === this.$route.params.userID)"
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
    </v-tab-item>
    </v-tabs>
    
  </base-section>
</template>

<script>
  import common from '@/common.vue'
import Section from '../../components/base/Section.vue'
  export default {
  components: { Section },
    name: 'SectionAboutOurProduct',

    data: () => ({
      userNickname: '',
      columns: null,
      bangumis:null,
      videos: null,
      moments: null,
      tab: null,
      items: null,
      text: null,
      
      favs: null,
      myname: '',
      hisname: '',
      content: '',
      charBoxes: null,
    }),
    methods: {
      isLogin: function () {
        return true
      },
      

      getUserVideos: function() {
        this.$axios
        .get('/user_id='+this.$route.params.userID + '/videos', {
        })
        .then(res => {
            this.videos = res.data
        })
        .catch(failResponse => {
            alert('vcideos get response failed')
        })
      },
      getUserMoments: function() {
        this.$axios
        .get('/user_id='+this.$route.params.userID + '/dynamics', {
        })
        .then(res => {
            this.moments = res.data
        })
        .catch(failResponse => {
            alert('moments get response failed')
        })
      },
      getUserColumns: function() {
        this.$axios
        .get('/user_id='+this.$route.params.userID + '/columns', {
        })
        .then(res => {
            this.columns = res.data
        })
        .catch(failResponse => {
            alert('columns get response failed')
        })
      },
      // getUserBangumis: function() {
      //   this.$axios
      //   .get('/user_id='+this.$route.params.userID + '/bangumis', {
      //   })
      //   .then(res => {
      //       this.bangumis = res.data
      //   })
      //   .catch(failResponse => {
      //       alert('bangumis get response failed')
      //   })
      // },
      getUserFavs: function() {
        this.$axios
        .get('/user_id='+this.$route.params.userID + '/favorites', {
        })
        .then(res => {
            this.favs = res.data
        })
        .catch(failResponse => {
            alert('fav get response failed')
        })
      },
      getUserInfo: function() {
        this.$axios
        .get('/userId='+this.$route.params.userID + '/info', 
         {headers : {token: this.$cookies.get("token")}})
        .then(res => {
          this.userNickname = res.data.data.nickname
        })
        .catch(failResponse => {
            alert('info get response failed')
        })
      },
      getCharBox: function() {
        this.$axios
        .get('/messages/with='+this.$route.params.userID, 
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
          targetUserId: this.$route.params.userID,
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
        this.getCharBox()
        
      }
      
    },
    created() {
      this.getUserInfo()
      this.getCharBox()
      this.getUserColumns()
      this.getUserFavs()
      this.getUserVideos()
      this.getUserMoments()
      // this.getUserBangumis()
    }
  }
</script>
<style>
/* Helper classes */
.basil {
  background-color: #f7def0 !important;
}
</style>
