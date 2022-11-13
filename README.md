# todo-server-with-java

Java 製の ToDo アプリケーション

# 開発文書

## 要求

- システムはタスクを登録できなければならない 
  - タスクにはタスク名を登録できなければならない
  - タスクには期限を登録できなければならない
  - タスクには完了状態（完了 or 未完了）を登録できなければならない
- システムはタスクの内容を編集できなければならない
- システムはタスクを削除できなければならない
- ユーザーはタスク一覧を閲覧できなければならない

## 用語

- タスク
- タスク名
- 期限
- 完了状態
- タスク一覧
- ユーザー

## ドメインモデル

![SoWkIImgAStDuKfCBialKdZSlEnnyvx7JTk095ToOb6AmkEc_O-RTZvkMe_6VJgX5oBoRCxybpCdGDXFr_KysTNsl6lRizPkDFLFTZIyMBQSYmlP1eLyNIJpqPG29R7AGiKHpa2LWhe7LH3LOGwfUIb0BmC0](https://user-images.githubusercontent.com/57878514/201529987-e56d5ccd-8aa5-4738-b8ba-809c97cacb49.png)

## ユースケース図

![SoWkIImgAStDuKfCBialKdZSlEnnyvx7JTk095TfSMfoOd6gmkEc_O-RTZvkMl-uSTtpcRjVzcp2BhLznSlPRLYbddPslPov1BjKVzFprkLSNCWsA-ZgOh4KGmccvHXAC1s1gGJJCGwfUId0C040](https://user-images.githubusercontent.com/57878514/201529980-3c1a2e0a-3de7-440c-8c64-cfd8e3310737.png)

## ユースケース記述とロバストネス分析

### ローカルファイルをデータベースとして利用するための初期化をする

| 項目     | 内容                |
|--------|-------------------|
| ユースケース | タスクの登録            |
| アクター   | ユーザー              |
| 事前条件   | タスク一覧画面が表示されていること |

#### 基本コース

1. ユーザーはタスク一覧画面の「タスク登録フォーム」の「タスク名」と「期限」を入力して「登録」ボタンを押下する
2. システムはタスク一覧画面の「タスク一覧」の先頭に、入力された内容のタスクを追加する

#### 代替コース（タスク名 or 期限が入力されなかった場合）

1. ユーザーはタスク一覧画面の「タスク登録フォーム」の「タスク名」のみを入力して「登録」ボタンを押下する
2. システムはタスク一覧画面の「タスク登録フォーム」の下部に「期限が入力されていません」とアラートメッセージを表示する

「タスク名」が入力されなかった場合も同様の表示をする

![TLB1Ji8m6BxtAHhkl00ddi0du5As9XAXIxfou6PRJGXWGemUY1Y4A68ya6XYK20ypC-0dNm5kysCAkEmxTlNx__MxrkBlY1Sr6jLH1pXSGnw37e1wiFSKTchCvVm1WQr0lK9QhgUdU_6pzkRUNW_GfI9Yh0MRS9I8yTZWdjLd4iNWkuLoCccFmXxNP3J4WikduBM7SXRK1sGOv0NeDeWsz-BGGuJ7pkdr3ax8JMwDzlsvc7d](https://user-images.githubusercontent.com/57878514/201530009-d8ee4e0c-c26c-4933-96f7-a29b4c0b96bd.png)
