<template>
  <base-section id="bangumis-row">
    <base-section-heading title="Best 8 Bangumis in Current Season">
      <div>
        <p>本季度的百合番又有哪些呢</p>
      </div>
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in features"
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
  </base-section>
</template>

<script>
  export default {
    name: 'SectionBangumisRow',
    created() {
      this.getBangumis()
    },
    data: () => ({
      features: null
    }),
    methods: {
      getBangumis: function () {
        this.$axios
          .get('bangumis/top8', {
          })
          .then(res => {
            this.features = res.data
          })
          .catch(failResponse => {
            alert('bangumis response failed')
          })
      },
    },
  }
</script>
