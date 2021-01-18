<template>
<v-container>
  <base-section id="bangumis-row">
    <base-section-heading title="收藏的番剧">
      <div>
        <p>都收藏了什么好看的啊</p>
      </div>
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in this.bangumis"
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
    </v-container>
  </base-section>

  <base-section id="videos-row">
    <base-section-heading title="收藏的视频">
      <div>
        <p>都收藏了什么好看的啊</p>
      </div>
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in this.videos"
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
                :to="'personal-inf/'+feature.userId"
              >
                作者：{{feature.nickname}}
              </v-btn>
            </v-btn-toggle>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>

    <base-section id="special-column">
    <base-section-heading title="收藏的专栏">
      都收藏了什么好看的啊
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in this.columns"
          :key="i"
          cols="12"
          md="6"
        >
          <base-avatar-card
            v-bind="feature"
            :icon="feature.icon"
            :title="feature.columnName"
            align="left"
            horizontal
          >
            <div>
                <p>{{feature.columnAbstract}}
                </p>
                <v-btn
                    text
                    :to="'personal-inf/'+feature.userId"
                >
                    作者: {{feature.nickname}}
                </v-btn>
                <v-divider
                  class="mx-4"
                  vertical
                ></v-divider>
                <v-btn
                    text
                    :to="'column-page/'+feature.columnId"
                >
                    click to have a look
                </v-btn>
            </div>
          </base-avatar-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</v-container>
</template>

<script>
  export default {
    name: 'SectionBangumisRow',
    created() {
      this.getAll()
    },
    data: () => ({
      bangumis: null,
      videos: null,
      columns: null
    }),
    methods: {
      getAll() {
        this.$axios
          .get('/favoriteId='+ this.$route.params.favId +'/content', {
          },{headers : {token: this.$cookies.get("token")}})
          .then(res => {
              this.bangumis = res.data.data.favoriteBangumis
              this.videos = res.data.data.favoriteVideos
              this.columns = res.data.data.favoriteColumns
          })
          .catch(failResponse => {
              alert('get all failed')
          })
      },
      
    },
  }
</script>
