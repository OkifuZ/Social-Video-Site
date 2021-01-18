<template>
  <base-section id="videos-row">
    <base-section-heading title="All Videos">
      <div>
        <p>所有视频都在这里唷</p>
      </div>
    </base-section-heading>

    <v-container>
      <!-- <v-row>
        <v-col
          v-for="(feature, i) in features"
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
              group
            >
              <v-btn
                value="left"
                :to="'video/'+feature.videoId"
              >
                点击观看
              </v-btn>
              
              <v-btn
                value="center"
                :to="'/personal-inf/'+feature.userId"
              >
                作者：{{feature.nickname}}
              </v-btn>
              
              <v-btn
                value="center"
                color="error"
                :to="'/personal-inf/'+feature.userId"
              >
                作者：{{feature.nickname}}
              </v-btn>
              
            </v-btn-toggle>
          </v-card>
        </v-col>
      </v-row> -->
      <v-row>
        <v-col
          v-for="(video, i) in features"
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
                :to="'video/'+video.videoId"
              >
                观看
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
  </base-section>
</template>

<script>
  export default {
    name: 'SectionVideosRow',
    created() {
      this.getAllVideos()
    },
    methods : {
      is_a_Admin: function() {
        return this.$cookies.get("isAdmin") === 'true'
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
      getAllVideos : function() {
        this.$axios
        .get('/videos', {
        })
        .then(res => {
            this.features = res.data
        })
        .catch(failResponse => {
            alert('videos get response failed')
        })
      }
    },
    data: () => ({
      features: null
    }),
  }
</script>
