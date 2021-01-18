<template>
  <base-section
    id="info-alt"
    space="56"
  >
    <v-container>
        <base-section-heading
            :title="'正在观看: ' + this.videoName"
            >
                <div class="text-center">
                <v-btn
                class="mx-2"
                fab
                dark
                large
                color="pink"
                @click="addVideoToFav()"
                >
                <v-icon dark>
                    mdi-heart
                </v-icon>

                </v-btn>
                <v-btn
                class="mx-2"
                fab
                dark
                large
                color="purple"
                :to="'/personal-inf/'+this.userId"
                >
                <v-icon dark>
                    mdi-account
                </v-icon>

                </v-btn>
                <v-btn
                class="mx-2"
                fab
                dark
                large
                v-if="this.liked"
                disabled
                :color="'grey lighten-1'"
                >
                <v-icon dark>
                    mdi-thumb-up
                </v-icon>
                </v-btn>
                <v-btn
                class="mx-2"
                fab
                dark
                large
                v-if="!this.liked"
                :color="'red lighten-1'"
                @click="addVideoToFav()"
                >
                <v-icon dark>
                    mdi-thumb-up
                </v-icon>
                </v-btn>
            </div>
        </base-section-heading>
    
        <v-card 
            max-width="900"
            class="mx-auto my-8"
        >
            <video-player  class="video-player vjs-custom-skin"
                ref="videoPlayer" 
                :playsinline="true" 
                :options="playerOptions"
            ></video-player>
        </v-card>
            <v-row justify="space-around" >
                
                <v-card
                    flat
                    max-width="800"
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
                        @click="gotoTag(tag.tagName)"
                    >
                        tag{{i+1}}: {{ tag.tagName }}
                    </v-chip>
                    
                    </v-chip-group>
                </v-card>
         </v-row>
        
        <v-col>
            <v-window
                v-model="window"
                vertical
                v-for="(com ,i) in comments"
                :key="i"
            >
                <v-window-item>
                <v-card flat>
                    <v-card-text>
                    <v-row
                        class="mb-4"
                        align="center"
                    >
                        <v-btn 
                            text
                            :to="'/personal-inf/'+com.userId"
                            >
                        <strong class="title">{{com.nickName}}</strong>
                        </v-btn>
                        发表了评论
                        <v-spacer></v-spacer>
                        <v-icon>mdi-account</v-icon>
                    </v-row>

                    <p>
                        {{com.message}}
                    </p>

                    </v-card-text>
                </v-card>
                </v-window-item>
                <v-divider />
            </v-window>
        </v-col>
        <v-card
        max-width="1200"
        flat
        class="mx-auto">
        <v-container
        v-if="isLogin()"
        >
        <v-toolbar
            flat
            color="primary"
            dark
        >
        <v-toolbar-title
        >
            以 {{getNickname()}} 的身份对此视频发表评论：
        </v-toolbar-title>
        </v-toolbar>
        <v-card-text>
        <v-textarea
            filled
            label="快写评论的内容呀"
            v-model="comment"
        ></v-textarea>
        </v-card-text>

        <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
            color="primary"
            depressed
            @click="sendComment"
        >
            发布动态
        </v-btn>
        </v-card-actions>
        </v-container>
        <v-container
        v-if="!isLogin()"
        >
        <v-toolbar
            flat
            color="primary"
            dark
        >
        <v-toolbar-title
        >
            请先登录再发评论喵
        </v-toolbar-title>
        </v-toolbar>
        </v-container>
        </v-card>
    </v-container>
  </base-section>
</template>

<script>
    import Divider from '../../components/base/Divider.vue'
    import {videoPlayer} from 'vue-video-player'
    import common from '@/common.vue'
    import router from '@/router'
    import Card from '../../components/base/Card.vue'
    export default {
        created() {
            this.getVideoById()
        },
  components: { Divider, videoPlayer },
         data: () => ({
            length: 3,
            window: 0,
            liked: false,
            comment: '',
            name: 'SectionVideoPlayer',
            videoName: '',
            comments: [
                {
                    nickName: 'Biden',
                    message: '12345678',
                    userId: 'sas',
                },
            ],
            tags: null,
            userId: null,
            playerOptions: {
                playbackRates: [0.5, 1.0, 1.5, 2.0], // 可选的播放速度
                autoplay: false, // 如果为true,浏览器准备好时开始回放。
                muted: false, // 默认情况下将会消除任何音频。
                loop: false, // 是否视频一结束就重新开始。
                preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
                language: 'zh-CN',
                aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                sources: [{
                    type: "video/mp4", // 类型
                    src: null, //url地址
                }],
                poster: null, // 封面地址
                width: 800,
                notSupportedMessage: '正在加载视频', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
                controlBar: {
                timeDivider: true, // 当前时间和持续时间的分隔符
                durationDisplay: true, // 显示持续时间
                remainingTimeDisplay: false, // 是否显示剩余时间功能
                fullscreenToggle: true, // 是否显示全屏按钮
                height: 800
                }
            }
        }),
        
        methods: {
            addVideoToFav() {
                this.$axios
                .post('like/videoId='+this.$route.params.videoID, 
                 {headers: {token: this.$cookies.get("token")}})
                .then(res => {
                    if (res.data.code === 200) {
                    alert(res.data.msg)
                    this.$router.go(0)
                    // this.$router.replace({ path: 'my-home' })
                    } else {
                        alert(res.data.msg)
                    }
                })
                .catch(failResponse => {
                    alert('LIKE VIDEO response failed')
                })
            },
            sendComment: function () {
                this.charBoxes.push({
                    nickName: 'Trump',
                    message: this.comment,
                })
                this.comment = ''
            },
            gotoTag: function(tagname) {
                this.$router.push({ path: '/tag-page' })
            },
            isLogin: function() {
                return this.$cookies.get('token') != null
            },
            getVideoById: function() {
                this.$axios
                .get('/videos/av='+this.$route.params.videoID, 
                    {headers : {token: this.$cookies.get("token")}} 
                )
                .then(res => {
                    if (res.data.code === 200) {
                        this.userId = res.data.data.userId
                        this.tags = res.data.data.tags
                        this.videoName = res.data.data.videoName
                        this.playerOptions.sources[0].src = res.data.data.videoContentUrl
                        this.playerOptions.poster = res.data.data.videoImageUrl
                        this.comments = res.data.data.comments
                        this.liked = res.data.data.likedByCurrentWatcher
                    } else {
                        alert(res.data.msg)
                    }
                })
                .catch(failResponse => {
                    alert('video get response failed')
                })
            },
            getNickname: function() {
                return this.$cookies.get('nickname')
            },
            getColor: function() {
                if (this.liked) {
                    return 'grey lighten-1'
                }
                else {
                    return 'red lighten-1'
                }
            }
            
        },
    }
</script>
