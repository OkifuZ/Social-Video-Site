<template>
  <base-section id="videos-row">
    <base-section-heading title="Top 8 Hot Videos">
      <div>
        <p>在时下热门的视频里碰碰运气</p>
      </div>
    </base-section-heading>

    <v-container>
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
            max-width="400"
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
                :to="'video/'+feature.videoId"
              >
                观看
              </v-btn>
              <v-btn
                value="center"
                :to="'/personal-inf/'+feature.userId"
              >
                作者：{{feature.nickname}}
              </v-btn>
            </v-btn-toggle>
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
      this.getVideosBest()
    },
    data: () => ({
      videos: null
    }),
    methods: {
      getVideosBest: function() {
        this.$axios.get('/videos/top8', {
        })
        .then(res => {
            this.videos = res.data
        })
        .catch(failResponse => {
            alert('videos get response failed')
        })
      }
    }
  }
</script>
