- getBangumis()

  全部番剧， 分类

  eg: 

  ```js
  [
      {
          cate: [
              {
                  title: '终将成为你aaaa',
                  description: 'yuri，世界で最高のものです',
                  bangumiId: 'kaj11kj',
              },
              {},...
          ],
          cateName: '百合',
      },
      {}...
  ]
  ```

- getBangumisBest()

  收藏数最多的8个番剧

  eg: 

  ```js
  [
      {
          title: '终将成为你a',
          description: 'yuri，世界で最高のものです',
          bangumiId: 'kaj11kj',
      },
      {},...
  ],
  ```

- getVideos()

  获取全部视频

  userId是作者nickname

  ```js
  [
      {
           videoID: 'ja7sh1oi11',
           title: '企鹅物流空酱完美演唱',
           description: '企鹅物流是物流公司，但每年的军火花销占了大部头，今日空酱在大地的尽头酒吧为我们带来了怎样的演唱',
           user: 'Biden',
           userID: 'uiasd8981',
       },
       {}...
  ],
  ```

- getVideosBest() 

  获取收藏数最多的8个视频

  user是作者nickname

  userId是作者主码

  ```js
   [
       {
           videoID: 'ja7sh1oi11',
           title: '企鹅物流空酱完美演唱',
           description: '企鹅物流是物流公司，但每年的军火花销占了大部头，今日空酱在大地的尽头酒吧为我们带来了怎样的演唱',
           user: 'Biden',
           userID: 'uiasd8981',
       },
       {}...
   ]
  ```

- getColumns()

  icon取值有

  mdi-account

  mdi-account-circle-outline

  mdi-account-circle

  mdi-account-box

  mdi-account-cowboy-hat

  mdi-account-reacitve

  mdi-airballoon

  mdi-adobe

  mdi-acrobat

  mdi-air-conditioner

  mdi-all-inclusive

  mdi-allergy

  mdi-alien-outline

  mdi-alien

  mdi-atom

  mdi-bacteria

  mdi-cards-club

  mdi-checkerboard

  ...

  https://materialdesignicons.com/

  random

  ```js
  [
          {
            title: '安达与岛村漫画动漫之家有备份，快去下载呀',
            abstract: '众所周知，陈睿总有一天会被钉在耻辱柱上，回不到过去那便毁灭现实吧，叔叔他只是资本家，共产主义的幽灵不会退散',
            icon: 'mdi-message-text',
            user: 'Trump',
            userId: 'ashi7771',
            columnId: '78asf9dy',
          },
      {}...
  ]
  ```

- getColumnsBest()

  获取最好的8个专栏

  同上

- getVideosByKeyword(keyword)

  同getVideos, keyword是搜索关键字

- getBangumisByKeyword(keyword)

  同getBangumis, keyword是搜索关键字

- getColumnsByKeyword(keyword)

  同getColumns, keyword是搜索关键字

- sendVideoComments(token, videoId, myComment, myNickname)

- sendMomentComments(token, momentId, myComment, myNickname)

- sendColumnComments(token, columnId, myComment, myNickname)

- getVideoInfByVideoId(videoId)

  ```js
      videoName: '安达与岛村',
          videoUrl: '../../assets/video.mp4'
  		videoPic: '../../assets/videoPic.jpg'
  		description: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'
          Comments: [
              {
                  nickName: 'Biden',
                  message: '12345678',
                  userId: 'sas',
              },
              {
                  nickName: 'Biden',
                  message: '12345678',
                  userId: 'sas',
              },
              {
                  nickName: 'Biden',
                  message: '12345678',
                  userId: 'sas',
              },
              {
                  nickName: 'Biden',
                  message: '12345678',
                  userId: 'sas',
              },
          ],
          tags: [
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
              { tagName: 'Dashboard'},
          ],
  ```
  


-----

postVideo: 添加视频

​	para: 

​		token: string

​		videoTitle: string

​		videoUrl: string

​		videoPicUrl: string

​		videoDes: string

​		newVideoTags: [string]

​	return: 

​		null



postMoment: 添加动态

​	para: 

​		token: string

​		momentText: string

​		newMomentTags: [string]

​	return: 

​		null



postColumn: 添加动态

​	para: 

​		token: string

​		columnTtile: string

​		columnText: string

​		newcolumnTags: [string]

​	return: 

​		null

---

getTagsByKeyword(keyword)

```js
[
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},
    { tagName: 'Dashboard'},

],
```

getUserFav(userId: string) 

```json
{
    [
    	{favId: '12212'}	
    ]
}
```





getUserFavColumns(userId: string)

返回这个用户的收藏的专栏， a list



getUserFavBangumis(userId: string)

返回这个用户追的番剧, a list



getUserFavVideos(userId: string)

返回这个用户收藏的视频, a list



getUserMoments(userId: stirng)

返回这个用户发布的的动态列表

```json
moments: [
    {
        title: '安达与岛村漫画动漫之家有备份，快去下载呀',
        abstract: '众所周知，陈睿总有一天会被钉在耻辱柱上，回不到过去那便毁灭现实吧，叔叔他只是资本家，共产主义的幽灵不会退散',
        icon: 'mdi-message-text',
        userNickname: 'Trump',
        momentId: 'aassqe112',
    },
    {},...
]
```



getUserOwnColumns(userId: string)

返回这个用户的写的专栏， a list



getUserOwnBangumis(userId: string)

返回这个用户发布的番剧, a list



getUserOwnVideos(userId: string)

返回这个用户上传的视频, a list



getMessages(

​	token: string

​	targetUserId: string 

)

```json
[
    {
        fromNum: 1, // 1 says me
        message: '12345678', // what I say
    },
    {
        fromNum: 2, // 2 says another man
        message: '12345678',
    },
    {
        fromNum: 1,
        message: '12345678',
    },

],
```



sendMessages(

​	token: string

​	targetUserId: string

​	messages: string 

)

发送消息



三个管理员桑可以进行的指令

deleteVideo(token: string, videoId: string)

deleteBangumi(token: string, bangumiId: string)

deleteColumn(token: string, columnId: string)

不设置标签删除的功能



getMyMessages(token: string)

接受我收到的所有消息

```json
[
    {
        from: 'Biden', // nickname
        fromId: 'as8dwd',
        time: '2020-1-20  11:30',
        message: 'Hi Trump, you lose, I win!!!',
    },
    {
        from: 'Biden',
        fromId: 'as8dwd',
        time: '2020-1-20  11:30',
        message: 'Hi Trump, fuck you',
    },
    {
        from: 'Biden',
        fromId: 'as8dwd',
        time: '2020-1-20  11:30',
        message: 'Hi Trump, you loser',
    },
],
```



getVideoComments(videoId: string)

getmomentComments(momentId: string)

getcolumnComments(columnId: string)

```json
[
    {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
    {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
    {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
    {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
],
```



sendVideoComment(token: string, videoId: string, content: text)

发表这个视频的评论

sendMomentComment(token: string, momentId: string, content: text)

发表这个视频的评论

sendColumnComment(token: string, columnId: string, content: text)

发表这个视频的评论



getMomentInf(momentId: string)

```json
{time: '2077-5-17 5:35',
 userNickname: 'Trump', // moment publisher
 content: '',
 comments: [
     {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
    {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
 ],
 tags: [
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},

 ],
}
```



getColumnInf(columnId: string)

```json
{time: '2077-5-17 5:35',
 title: 'tilelelelel',
 userNickname: 'Trump', // moment publisher
 content: '',
 comments: [
     {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
    {
        nickName: 'Biden',
        message: '12345678',
        userId: 'sas',
    },
 ],
 tags: [
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},
     { tagName: 'Dashboard'},

 ],
}
```



getUserName(userId)

返回nickname



getUserFav(userId)



getFavContent(favId)

```json

```





addVideoToFav(token, videoId, favName)

addBangumiToFav(token, bangumiId, favName)

addColumnToFav(token, columnId, favName)