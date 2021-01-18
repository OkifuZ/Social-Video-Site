<template>

  <base-section id="videos-row">
  
    <base-section-heading 
    :title="'当前搜索内容：' + $route.params.search">
      <div>
        <p>{{$route.params.search}} 是一个不错的关键词</p>
      </div>
    </base-section-heading>
    <base-subheading 
        align="center"
        :title="'关键词 ' + $route.params.search + ' 关联的标签'">
    </base-subheading>
    <v-row justify="space-around" >
      <v-card
          flat
          max-width="auto"
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
    <base-subheading 
        align="center"
        :title="'关键词 ' + $route.params.search + ' 关联的视频'">
    </base-subheading>
    <v-container>
      <v-row>
        <v-col
          v-for="(video, i) in videos"
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
              :src="video.videoImageUrl"
            ></v-img>
            <v-card-title>
              {{video.title}}
            </v-card-title>
            <v-card-text>
              <div><div class="">{{video.videoDescription}}</div></div>
            </v-card-text>
            <v-btn-toggle
              tile
              group
            >
              <v-btn
                value="left"
                :to="'/video/'+video.videoId"
              >
                观看
              </v-btn>
              <v-btn value="center">
                收藏
              </v-btn>
            </v-btn-toggle>
            <v-btn 
                v-if="is_a_Admin()"
                color = "error"
                @click="deleteVideo(video.videoId)"
                flat
              >
              <h3>删除</h3>
              <v-icon
                dark
                right
              >
              mdi-cancel
              </v-icon>
            </v-btn>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <base-subheading 
        align="center"
        :title="'关键词 ' + $route.params.search + ' 关联的番剧'">
    </base-subheading>
    <v-container>
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
          >
            <v-img
              height="250"
              :src="feature.bangumiCoverUrl"
            ></v-img>
            <v-card-title>
              {{feature.title}}
            </v-card-title>
            <v-card-text>
              <div><div class="">{{feature.bangumiDescription}}</div></div>
            </v-card-text>
            <v-btn-toggle
              tile
              group
            >
              <v-btn
                value="left"
                :to="'/bangumi-play/'+feature.bangumiId"
              >
                观看
              </v-btn>
              <v-btn value="center">
                追番
              </v-btn>
            </v-btn-toggle>
              <v-btn 
                v-if="is_a_Admin()"
                color = "error"
                @click="deleteBangumi(feature.bangumiId)"
                flat
              >
              <h3>删除</h3>
              <v-icon
                dark
                right
              >
              mdi-cancel
              </v-icon>
            </v-btn>
            
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <base-subheading 
        align="center"
        :title="'关键词 ' + $route.params.search + ' 关联的专栏'">
    </base-subheading>
    <v-container>
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
            horizontal
            :icon="feature.icon"
            :title="feature.columnName"
          >
            <div>
                <p>{{feature.columnAbstract}}
                </p>
                <v-btn
                    text
                    :to="'/personal-inf/'+feature.userId"
                >
                    作者: {{feature.nickname}}
                </v-btn>
                <v-divider
                  class="mx-4"
                  vertical
                ></v-divider>
                <v-btn
                    text
                    :to="'/column-page/'+feature.columnId"
                >
                    click to have a look
                </v-btn>
                <v-btn 
                v-if="is_a_Admin()"
                color="error"
                @click="deleteColumn(feature.columnId)"
              >
                <h3>删除</h3>
                <v-icon
                  dark
                  right
                >
                mdi-cancel
                </v-icon>
              </v-btn>

            </div>
          </base-avatar-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  // import common from '@/common.vue'
  export default {
    name: 'SectionVideosRow',
    created() {
      this.getBangumisByKw()
      this.getVideosByKw()
      this.getColumnsByKw()
      this.getTagsByKw()
    },
    data: () => ({
        search : '$route.params.search',        
        tags: [
                { tagName: 'Dashboard'},
                { tagName: 'Dashboard'},
                
                
            ],
      videos: null,
      // [
      //   {
      //     videoID: 'ja7sh1oi11',
      //     title: '企鹅物流空酱完美演唱',
      //     description: '企鹅物流是物流公司，但每年的军火花销占了大部头，今日空酱在大地的尽头酒吧为我们带来了怎样的演唱',
      //   },
        
      // ],
      bangumis:  null, 
      // [
      //   {
      //     videoID: 'ja7sh1oi11',
      //     title: '企鹅物流空酱完美演唱',
      //     description: '企鹅物流是物流公司，但每年的军火花销占了大部头，今日空酱在大地的尽头酒吧为我们带来了怎样的演唱',
      //   },
        
      // ],
      columns: null
    }),
      methods: {
        is_a_Admin: function() {
          return this.$cookies.get("isAdmin")
        },
        gotoTag: function(tagname) {
          this.$router.push({ path: '/tag-page/'+tagname })
        },
        deleteVideo: function(videoId) {
          this.$axios
          .delete('admin/delete/videoId='+videoId, {            
            headers: {token: this.$cookies.get("token")}
          })
          .then(res => {
            if (res.data.code === 200) {
              this.$router.go(0)
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('delete video response failed')
          })
        },
        deleteBangumi: function(bangumiId) {
          this.$axios
          .delete('admin/delete/bangumiId='+bangumiId, {            
            headers: {token: this.$cookies.get("token")}
          })
          .then(res => {
            if (res.data.code === 200) {
              this.$router.go(0)
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('delete bangumi response failed')
          })
        },
        deleteColumn: function(columnId) {
          this.$axios
          .delete('admin/delete/columnId='+columnId, {            
            headers: {token: this.$cookies.get("token")}
          })
          .then(res => {
            if (res.data.code === 200) {
              this.$router.go(0)
            } else {
              alert(res.data.msg)
            }
          })
          .catch(failResponse => {
            alert('delete column response failed')
          })
        },
        getBangumisByKw() {
          this.$axios
            .get('/bangumis/keyword/'+this.$route.params.search, {
            })
            .then(res => {
                this.bangumis = res.data
            })
            .catch(failResponse => {
                alert('get bangumis failed')
            })
        },
        getColumnsByKw() {
          this.$axios
            .get('/columns/keyword/'+this.$route.params.search, {
            })    
            .then(res => {
                this.columns = res.data
            })
            .catch(failResponse => {
                alert('get column failed')
            })
        },
        getVideosByKw() {
          this.$axios
            .get('/videos/keyword/'+this.$route.params.search, {
            })
            .then(res => {
                this.videos = res.data
            })
            .catch(failResponse => {
                alert('get video failed')
            })
        },
        getTagsByKw() {
          this.$axios
            .get('/tags/keyword='+this.$route.params.search, {
            })
            .then(res => {
                this.tags = res.data
            })
            .catch(failResponse => {
                alert('get tags failed')
            })
        },
        
      },
  }
</script>
