<template>
  <base-section id="videos-row">
    <base-section-heading 
    :title="'当前标签：' + tag">
      <div>
        <p>{{tag}} 这个标签有什么好东西呢</p>
      </div>
    </base-section-heading>
    <base-subheading 
        align="center"
        :title="'含有 ' + tag + ' 标签的视频'">
    </base-subheading>
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
              <div><div class="">{{feature.VideoDescription}}</div></div>
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
    </v-container>
    <base-subheading 
        align="center"
        :title="'含有 ' + tag + ' 标签的番剧'">
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
                :to="'/bangumi-play/'+feature.bangumiId"
              >
                观看
              </v-btn>
            </v-btn-toggle>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
    <base-subheading 
        align="center"
        :title="'含有 ' + tag + ' 标签的专栏'">
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
            :title="feature.columnName"
            :icon="feature.icon"
            horizontal
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
            </div>
          </base-avatar-card>
        </v-col>
      </v-row>
    </v-container>
  </base-section>
</template>

<script>
  export default {
    name: 'SectionVideosRow',
  created() {
    this.getAll()
  },
    data: () => ({
        tag: 'tagname',

        videos: null,
        bangumis: null,
        columns: null,
    }),
    methods: {
      getAll: function () {
        this.$axios
          .get('tag-related-content/tagId='+this.$route.params.tagId, {
          })
          .then(res => {
            if (res.data.code == 200) {
              this.videos = res.data.data.tagRelatedVideos
              this.bangumis = res.data.data.tagRelatedBangumis
              this.columns = res.data.data.tagRelatedColumns
            }
          })
          .catch(failResponse => {
            alert('tag related response failed')
          })
      },
    },
  }
</script>
