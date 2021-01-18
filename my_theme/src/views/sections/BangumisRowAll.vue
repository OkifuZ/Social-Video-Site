<template>
    <div>
        <base-section
            id="bangumis-row-all"
            v-for="(category, index) in bangumis"
            :key="index"
        >
            <base-section-heading
                :title="'番剧类型: '+category.cateName"
            >
                <div>
                    <p>所有关于"{{category.cateName}}"的番剧</p>
                </div>
            </base-section-heading>

            <v-container>
            <v-row>
                <v-col
                v-for="(bangumi, i) in category.cate"
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
                    :src="bangumi.bangumiCoverUrl"
                    ></v-img>
                    <v-card-title>
                    {{bangumi.bangumiName}}
                    </v-card-title>
                    <v-card-text>
                    <div><div class="">{{bangumi.bangumiDescription}}</div></div>
                    </v-card-text>
                    <v-btn-toggle
                    v-model="text"
                    tile
                    color="deep-purple accent-3"
                    group
                    >
                    <v-btn
                        value="left"
                        :to="'bangumi-play/'+bangumi.bangumiId"
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
                        @click="deleteBangumi(bangumi.bangumiId)"
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
    </div>
</template>

<script>
  export default {
    name: 'SectionBangumisRowAll',
    created() {
      
      this.getAllBangumis()
     
    },
    data: () => ({
      bangumis: null
    }), 

    methods: {
      is_a_Admin: function() {
        return this.$cookies.get("isAdmin")
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
      getAllBangumis: function () {
        this.$axios
          .get('bangumis/cate', {
          })
          .then(res => {
            this.bangumis = res.data
          })
          .catch(failResponse => {
            alert('bangumis all response failed')
          })
      },
    },
  }
</script>
