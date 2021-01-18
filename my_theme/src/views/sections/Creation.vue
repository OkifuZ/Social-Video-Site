<template>
  <v-card>
    <v-toolbar
      flat
      color="primary"
      dark
    >
      <v-toolbar-title>创作栏</v-toolbar-title>
    </v-toolbar>
    <v-tabs vertical>
      <v-tab>
        <v-icon left>
          mdi-account
        </v-icon>
        发布番剧
      </v-tab>
      <v-tab>
        <v-icon left>
          mdi-account
        </v-icon>
        上传视频
      </v-tab>
      <v-tab>
        <v-icon left>
          mdi-lock
        </v-icon>
        发布动态
      </v-tab>
      <v-tab>
        <v-icon left>
          mdi-access-point
        </v-icon>
        新建专栏
      </v-tab>
      <v-tab-item>
        <v-card flat>
          <base-section
              id="info-alt"
              space="56"
            >
              <v-container>
                <base-section-heading
                  title="发布番剧"
                  >
                </base-section-heading>
                <v-row justify="space-between">
                  <v-col
                    cols="12"
                    md="6"
                  >
                      <v-card
                        class="mx-auto"
                        max-width="800"
                      >
                        <v-toolbar
                          color="primary"
                          dark
                        >

                          <v-btn
                            icon
                            disabled
                          >
                            <v-icon>mdi-plus</v-icon>
                          </v-btn>
                          <v-toolbar-title>发布番剧协议</v-toolbar-title>

                          <v-spacer></v-spacer>

                        </v-toolbar>
                        <v-subheader>请点击选框以表示您同意视频发布协议</v-subheader>
                        <v-divider></v-divider>

                        <v-list
                          flat
                          subheader
                        >
                          <v-subheader>番剧发布协议书</v-subheader>

                          <v-list-item-group
                            v-model="settings"
                            multiple
                            active-class=""
                          >
                            <v-list-item>
                              <template v-slot:default="{ active }">
                                <v-list-item-action>
                                  <v-checkbox :input-value="active"
                                  v-model="checked3"></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                  <v-list-item-title>番剧所有权</v-list-item-title>
                                  <v-list-item-subtitle>您发布的视频的版权都在我这里，绝对不在陈睿那里</v-list-item-subtitle>
                                </v-list-item-content>
                              </template>
                            </v-list-item>

                            <v-list-item>
                              <template v-slot:default="{ active }">
                                <v-list-item-action>
                                  <v-checkbox 
                                  :input-value="active"
                                  v-model="checked4"
                                  ></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                  <v-list-item-title>番剧发布方法</v-list-item-title>
                                  <v-list-item-subtitle>您发布的视频需要填写各种信息，详细请看右侧，记得选择一些tag好让别人找到你的番剧</v-list-item-subtitle>
                                </v-list-item-content>
                              </template>
                            </v-list-item>

                            
                            
                          </v-list-item-group>
                        </v-list>
                      </v-card>
                  </v-col>

                  <v-col
                    cols="12"
                    md="6"
                  >
                    <div>
                      <base-info-card
                        title="填写番剧具体信息"
                        space="4"
                        color="primary"
                      />

                      <!-- <base-text-field 
                      label="番剧的标题..." 
                      v-model="videoTitle"
                      /> -->
                      <v-text-field
                        label="番剧的标题..." 
                      v-model="videoTitle"
                        outlined
                      ></v-text-field>
                      <!-- <base-text-field label="视频的URL..."
                      v-model="videoUrl"
                      />

                      <base-text-field label="番剧封面的URL"
                      v-model="videoPicUrl"
                       /> -->
                       
                      <v-text-field
                        label="视频的URL..."
                        v-model="videoUrl"
                        outlined
                      ></v-text-field>
                      <v-text-field
                        label="番剧封面的URL"
                        v-model="videoPicUrl"
                        outlined
                      ></v-text-field> 
                      <v-text-field
                        label="番剧的分类"
                        v-model="bangumiCate"
                        outlined
                      ></v-text-field>
                      <v-container fluid>
                        <v-combobox
                          v-model="newVideoTags"
                          :items="items"
                          :search-input.sync="search"
                          hide-selected
                          hint="加几个都可以哟"
                          label="添加一些标签吧"
                          multiple
                          persistent-hint
                          small-chips
                        >
                          <template v-slot:no-data>
                            <v-list-item>
                              <v-list-item-content>
                                <v-list-item-title>
                                  没有匹配的标签 "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                                </v-list-item-title>
                              </v-list-item-content>
                            </v-list-item>
                          </template>
                        </v-combobox>
                      </v-container>
                      

                      <base-textarea
                        class="mb-6"
                        label="写一些番剧的简介吧..."
                        v-model="videoDes"
                        
                      />

                      <base-btn
                        :color="primary"
                        outlined
                        target="_blank"
                        @click="postBangumi"
                      >
                        发布番剧
                      </base-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-container>
            </base-section>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <base-section
              id="info-alt"
              space="56"
            >
              <v-container>
                <base-section-heading
                  title="上传视频喵"
                  >
                </base-section-heading>
                <v-row justify="space-between">
                  <v-col
                    cols="12"
                    md="6"
                  >
                      <v-card
                        class="mx-auto"
                        max-width="800"
                      >
                        <v-toolbar
                          color="primary"
                          dark
                        >

                          <v-btn
                            icon
                            disabled
                          >
                            <v-icon>mdi-plus</v-icon>
                          </v-btn>
                          <v-toolbar-title>发布视频协议</v-toolbar-title>

                          <v-spacer></v-spacer>

                        </v-toolbar>
                        <v-subheader>请点击选框以表示您同意视频发布协议</v-subheader>
                        <v-divider></v-divider>

                        <v-list
                          flat
                          subheader
                        >
                          <v-subheader>视频发布协议书</v-subheader>

                          <v-list-item-group
                            v-model="settings"
                            multiple
                            active-class=""
                          >
                            <v-list-item>
                              <template v-slot:default="{ active }">
                                <v-list-item-action>
                                  <v-checkbox :input-value="active"
                                  v-model="checked1"></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                  <v-list-item-title>视频所有权</v-list-item-title>
                                  <v-list-item-subtitle>您发布的视频的所有权都在我这里</v-list-item-subtitle>
                                </v-list-item-content>
                              </template>
                            </v-list-item>

                            <v-list-item>
                              <template v-slot:default="{ active }">
                                <v-list-item-action>
                                  <v-checkbox 
                                  :input-value="active"
                                  v-model="checked2"
                                  ></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                  <v-list-item-title>视频发布方法</v-list-item-title>
                                  <v-list-item-subtitle>您发布的视频需要填写各种信息，详细请看右侧，记得选择一些tag好让别人找到你的视频</v-list-item-subtitle>
                                </v-list-item-content>
                              </template>
                            </v-list-item>

                            
                            
                          </v-list-item-group>
                        </v-list>
                      </v-card>
                  </v-col>

                  <v-col
                    cols="12"
                    md="6"
                  >
                    <div>
                      <base-info-card
                        title="填写视频具体信息"
                        space="4"
                        color="primary"
                      />

                      <v-text-field 
                      outlined
                      label="视频的标题..." 
                      v-model="videoTitle"
                      />

                      <v-text-field label="视频的URL..."
                      outlined
                      v-model="videoUrl"
                      />

                      <v-text-field label="视频封面的URL"
                      outlined
                      v-model="videoPicUrl"
                       />
                      <v-container fluid>
                        <v-combobox
                          v-model="newVideoTags"
                          :items="items"
                          :search-input.sync="search"
                          hide-selected
                          hint="加几个都可以哟"
                          label="添加一些标签吧"
                          multiple
                          persistent-hint
                          small-chips
                        >
                          <template v-slot:no-data>
                            <v-list-item>
                              <v-list-item-content>
                                <v-list-item-title>
                                  没有匹配的标签 "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                                </v-list-item-title>
                              </v-list-item-content>
                            </v-list-item>
                          </template>
                        </v-combobox>
                      </v-container>

                      <base-textarea
                        class="mb-6"
                        label="写一些视频的简介吧..."
                        v-model="videoDes"
                      />

                      <base-btn
                        :color="primary"
                        outlined
                        target="_blank"
                        @click="postVideo"
                      >
                        发布视频
                      </base-btn>
                    </div>
                  </v-col>
                </v-row>
              </v-container>
            </base-section>
        </v-card>
      </v-tab-item>
      <v-tab-item>
        <v-card flat>
          <v-divider 
          :inset="true"/>
          <v-card>
          <v-toolbar
            flat
            color="primary"
            dark
          >
            <v-toolbar-title
            >发布一个动态</v-toolbar-title>
          </v-toolbar>
          </v-card>
          <v-card-text>
            <v-textarea
              filled
              label="快写动态的内容呀"
              v-model="MomentText"
            ></v-textarea>

            <v-divider class="my-2"></v-divider>
            <v-container fluid>
              <v-combobox
                v-model="newMomentTags"
                :items="items"
                :search-input.sync="search"
                hide-selected
                hint="加几个都可以哟"
                label="为动态添加一些标签吧"
                multiple
                persistent-hint
                small-chips
              >
                <template v-slot:no-data>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>
                        没有匹配的标签 "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-combobox>
            </v-container>
            
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              depressed
              @click="postMoment"
            >
              发布动态
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-tab-item>
      
      <v-tab-item>
        <v-card flat>
          <v-divider 
          :inset="true"/>
          <v-card>
          <v-toolbar
            flat
            color="primary"
            dark
          >
            <v-toolbar-title
            >发布一个专栏</v-toolbar-title>
          </v-toolbar>
          </v-card>
          <v-card-text>
            <v-text-field
              filled
              label="专栏标题"
              value="选什么标题好呢..."
              v-model="columnTitle"
            ></v-text-field>

            <v-textarea
              filled
              label="快写专栏的内容呀"
              value="  Duis aute irure dolor in reprehenderit in voluptate velit esse"
              v-model="columnText"
            ></v-textarea>

            <v-divider class="my-2"></v-divider>

            <v-container fluid>
              <v-combobox
                v-model="newColumnTags"
                :items="items"
                :search-input.sync="search"
                hide-selected
                hint="加几个都可以哟"
                label="为专栏添加一些标签吧"
                multiple
                persistent-hint
                small-chips
              >
                <template v-slot:no-data>
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>
                        没有匹配的标签 "<strong>{{ search }}</strong>". Press <kbd>enter</kbd> to create a new one
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-combobox>
            </v-container>
            
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
              color="primary"
              depressed
              @click="postColumn"
            >
              新建专栏
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-tab-item>
    </v-tabs>
  </v-card>
</template>

<script>
  import common from '@/common.vue'
  export default {
    name: 'SectionAboutOurProduct',

    data: () => ({
      checked1: false,
      checked2: false,
      checked3: false,
      checked4: false,
      userNickname: 'Trump',
      items: ['jump', '新番', '学习', '波兰蠢驴', '种田', ''],
      newVideoTags: ['2077'],
      search: null,
      bangumiCate: '',
      videoUrl : '',
      videoPicUrl : '',
      videoTitle : '',
      videoDes : '',

      MomentText: '',
      newMomentTags: ['gossip'],

      columnTitle: '',
      columnText: '',
      newColumnTags: ['评价'],
     
    }),
    methods: {
      isLogin: function () {
        return true
      },

      postVideo: function() {
        if (this.checked1 & this.checked2) {
          this.$axios
          .post('videos/post', {
            videoTitle: this.videoTitle,
            videoUrl: this.videoUrl,
            videoPicUrl: this.videoPicUrl,
            videoDes: this.videoDes,
            newVideoTags: this.newVideoTags,
          }, {headers: {token: this.$cookies.get("token")}})
          .then(res => {
            if (res.data.code === 200) {
              this.$router.replace({ path: 'my-home' })
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('post video response failed')
          })
        }
        else {
          alert('you must check agree')
        }
      },

      postBangumi: function() {
        if (this.checked3 & this.checked4) {
          this.$axios
          .post('bangumis/post', {
            bangumiTitle: this.videoTitle,
            bangumiUrl: this.videoUrl,
            bangumiPicUrl: this.videoPicUrl,
            bangumiDes: this.videoDes,
            newBangumiTags: this.newVideoTags,
            bangumiCategory: this.bangumiCate,
          }, {headers: {token: this.$cookies.get("token")}})
          .then(res => {
            if (res.data.code === 200) {
              this.$router.replace({ path: 'my-home' })
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('post bangumi response failed')
          })
        }
        else {
          alert('you must check agree')
        }
      },
      
      postMoment: function() {
          this.$axios
          .post('dynamics/post', {
            momentText: this.MomentText,
            newMomentTags: this.newMomentTags
          }, {headers: {token: this.$cookies.get("token")}})
          .then(res => {
            if (res.data.code === 200) {
              // this.$router.replace({ path: 'my-home' })
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('post moment response failed')
          })
      },

      postColumn: function() {
          this.$axios
          .post('columns/post', {
            columnTitle: this.columnTitle,
            columnText: this.columnText,
            columnPicUrl: 'http://217.69.9.116:164/images/3.png',
            newColumnTags: this.newColumnTags,
          }, {headers: {token: this.$cookies.get("token")}})
          .then(res => {
            if (res.data.code === 200) {
              // this.$router.replace({ path: 'my-home' })
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('post column response failed')
          })
      }
    },
  }
</script>
<style>
/* Helper classes */
.basil {
  background-color: #f7def0 !important;
}
</style>
