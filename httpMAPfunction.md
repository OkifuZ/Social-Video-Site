`StdType<T>` = `{T data, int code, String msg}`

| HTTP   | URL                                   | FunctionName              | Parameter                                                | ReturnType                         | Token               |
| ------ | ------------------------------------- | ------------------------- | -------------------------------------------------------- | ---------------------------------- | ------------------- |
| GET    | `/api/bangumis/cate`                  | `getBangumisByCate`       | `void`                                                   | `List<BangumisByCateResult>`       | `PassToken`         |
| POST   | `/api/login`                          | `login`                   | `LoginRequestUser` = `{username, password}`              | `StdType<String>`                  | `PassToken`         |
| POST   | `/api/register`                       | `register`                | `RegisterRequestUser` = `{username, password, nickname}` | `StdType<null>`                    | `PassToken`         |
| GET    | `/api/bangumis/top8`                  | `getBangumisTop8`         | `void`                                                   | `List<Bangumi>`                    | `PassToken`         |
| GET    | `/api/videos`                         | `getVideos`               | `void`                                                   | `List<VideoResult>`                | `PassToken`         |
| GET    | `/api/videos/top8`                    | `getVideosTop8`           | `void`                                                   | `List<VideoResult>`                | `PassToken`         |
| GET    | `api/columns`                         | `getColumns`              | `void`                                                   | `List<ColumnResult>`               | `PassToken`         |
| GET    | `api/columns/top8`                    | `getColumnsTop8`          | `void`                                                   | `List<ColumnResult>`               | `PassToken`         |
| GET    | `api/videos/keyword/{keyword}`        | `getVideosByKeyword`      | `void`                                                   | `List<VideoResult>`                | `PassToken`         |
| GET    | `api/columns/keyword/{keyword}`       | `getColumnsByKeyword`     | `void`                                                   | `List<ColumnResult>`               | `PassToken`         |
| GET    | `api/bangumis/keyword/{keyword}`      | `getBangumisByKeyword`    | `void`                                                   | `List<Bangumi>`                    | `PassToken`         |
| GET    | `api/videos/av={id}`                  | `getVideoById`            | `void`                                                   | `StdType<Video>`                   | `PassToken`         |
| POST   | `api/videos/post`                     | `postVideo`               | `PostVideoRequest`                                       | `StdType<>`                        | `NeedToken`         |
| DELETE | `"api/admin/delete/videoId={id}"`     | `deleteVideo`             | `id`                                                     | `StdType<>`                        | `AsAdmin`           |
| DELETE | `"api/admin/delete/bangumiId={id}"`   | `deleteBangumi`           | `id`                                                     | `StdType<>`                        | `AsAdmin`           |
| DELETE | `"api/admin/delete/columnId={id}"`    | `deleteColumn`            | `id`                                                     | `StdType<>`                        | `AsAdmin`           |
| DELETE | `"api/admin/delete/dynamicId={id}"`   | `deleteDynamic`           | `id`                                                     | `StdType<>`                        | `AsAdmin`           |
| GET    | `/api/bangumis/id={id}`               | `getBangumiById`          | `id`                                                     | `StdType<BangumiContentResult>`    | **`OptionalToken`** |
| POST   | `/api/bangumis/post`                  | `postBangumi`             | `PostBangumiRequest`                                     | `StdType<>`                        | `AsAdmin`           |
| GET    | `/api/columns/id={id}`                | `getColumnById`           | `id`                                                     | `StdType<ColumnContentResult`      | **`OptionalToken`** |
| POST   | `/api/columns/post`                   | `postColumn`              | `PostColumnRequest`                                      | `StdType<>`                        | `NeedToken`         |
| GET    | `/api/videos/id={id}`                 | `getVideoById`            | `id`                                                     | `StdType<VideoContentResult>`      | **`OptionalToken`** |
| POST   | `/api/videos/post`                    | `postVideo`               | `PostVideoRequest`                                       | `StdType<>`                        | `NeedToken`         |
| GET    | `/api/dynamics/id={id}`               | `getDynamicById`          | `id`                                                     | `StdType<DynamicContentResult>`    | **`OptionalToken`** |
| POST   | `/api/dynamics/post`                  | `postDynamic`             | `PostDynamicRequest`                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/comments/post/video`            | `postCommentToVideo`      | `PostCommentRequest`                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/comments/post/column`           | `postCommentToColumn`     | `PostCommentRequest`                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/comments/post/dynamic`          | `postCommentToDynamic`    | `PostCommentRequest`                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/comments/post/bangumi`          | `postCommentToBangumi`    | `PostCommentRequest`                                     | `StdType<>`                        | `NeedToken`         |
| GET    | `/api/comments/get/videoId={id}`      | `getVideoComments`        | `id`                                                     | `List<CommentResult>`              | `PassToken`         |
| GET    | `/api/comments/get/columnId={id}`     | `getColumnComments`       | `id`                                                     | `List<CommentResult>`              | `PassToken`         |
| GET    | `/api/comments/get/dynamicId={id}`    | `getDynamicComments`      | `id`                                                     | `List<CommentResult>`              | `PassToken`         |
| GET    | `/api/comments/get/bangumiId={id}`    | `getBangumiComments`      | `id`                                                     | `List<CommentResult>`              | `PassToken`         |
| GET    | `/api/messages/with={id}`             | `getMessagesWith`         | `id`                                                     | `List<UserMessageResult>`          | `NeedToken`         |
| POST   | `api/messages/send`                   | `sendMessageTo`           | `PostMessageRequest`                                     | `StdType<>`                        | `NeedToken`         |
| GET    | `api/messages/all`                    | `getConversations`        | `void`                                                   | `List<UserConversationResult>`     | `NeedToken`         |
| GET    | `api/tags/keyword={keyword}`          | `getTagsByKeyword`        | `keyword`                                                | `List<Tag>`                        | `PassToken`         |
| GET    | `api/user_id={id}/dynamics`           | `getUserDynamics`         | `id`                                                     | `List<UserDynamicResult>`          | `PassToken`         |
| GET    | `api/user_id={id}/columns`            | `getUserColumns`          | `id`                                                     | `List<UserColumnResult>`           | `PassToken`         |
| GET    | `api/user_id={id}/videos`             | `getUserVideos`           | `id`                                                     | `List<UserVideoResult>`            | `PassToken`         |
| GET    | `api/user_id={id}/favorites`          | `getUserFavorites`        | `id`                                                     | `List<UserFavoriteResult>`         | `PassToken`         |
| GET    | `api/favoriteId={id}/content`         | `getFavoriteContent`      | `id`                                                     | `StdType<FavoriteContentResult>`   | `PassToken`         |
| GET    | `api/userId={id}/info`                | `getUserInfo`             | `id`                                                     | `StdType<UserPublicInfoResult>`    | **`OptionalTOken`** |
| POST   | `api/followship/follow/userId={id}`   | `follow`                  | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `api/followship/unfollow/userId={id}` | `unfollow`                | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| GET    | `/api/history`                        | `getHistories`            | `void`                                                   | `HistroryResult`                   | `NeedToken`         |
| POST   | `/api/like/videoId={id}`              | `likeVideo`               | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/like/bangumiId={id}`            | `likeBangumi`             | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/like/columnId={id}`             | `likeColumn`              | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/like/commentId={id}`            | `likeComment`             | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `/api/like/dynamicId={id}`            | `likeDynamic`             | `id`                                                     | `StdType<>`                        | `NeedToken`         |
| POST   | `api/favorite/video`                  | `favoriteVideo`           | `FavoriteRequest`                                        | `StdType<>`                        | `NeedToken`         |
| POST   | `api/favorite/bangumi`                | `favoriteBangumi`         | `FavoriteRequest`                                        | `StdType<>`                        | `NeedToken`         |
| POST   | `api/favorite/column`                 | `favoriteColumn`          | `FavoriteRequest`                                        | `StdType<>`                        | `NeedToken`         |
| GET    | `api/tag-related-content/tagId={id}`  | `getTagRelatedContent`    | `id`                                                     | `StdType<TagRelatedContentResult>` | `PassToken`         |
| GET    | `api/user_id={id}/bangumis`           | `getUserFavoriteBangumis` | `id`                                                     | `List<BangumiResult>`              | `PassToken`         |
|        |                                       |                           |                                                          |                                    |                     |
|        |                                       |                           |                                                          |                                    |                     |
|        |                                       |                           |                                                          |                                    |                     |

