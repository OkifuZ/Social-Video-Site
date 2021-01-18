<template>
  <base-section id="special-column">
    <base-section-heading title="Top 6 Special Columns">
      当前阅读量最多的六个专栏唷
    </base-section-heading>

    <v-container>
      <v-row>
        <v-col
          v-for="(feature, i) in features"
          :key="i"
          cols="12"
          md="6"
        >
          <base-avatar-card
            v-bind="feature"
            align="left"
            horizontal
            :title="feature.columnName"
            :icon="feature.icon"
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
</template>

<script>
  export default {
    name: 'SectionSpecialColumn',
    created() {
      this.getColumnsBest()
    },
    data: () => ({
      features: null
    }),

    methods: {
      getColumnsBest: function() {
        this.$axios
        .get('/columns/top8', {
        })
        .then(res => {
            this.features = res.data
        })
        .catch(failResponse => {
            alert('columns get response failed')
        })
      }
    }
  }
</script>
