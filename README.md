# RoomPractice
android jetpack room 연습 프로젝트

## build.gradle
```
      def room_version = "2.2.5"
      implementation "androidx.room:room-runtime:$room_version"
      annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor
      // optional - Kotlin Extensions and Coroutines support for Room
      implementation "androidx.room:room-ktx:$room_version"
      // optional - RxJava support for Room
      implementation "androidx.room:room-rxjava2:$room_version"
      // optional - Guava support for Room, including Optional and ListenableFuture
      implementation "androidx.room:room-guava:$room_version"
      // Test helpers
      testImplementation "androidx.room:room-testing:$room_version"
```

## 메니페스트
  - android:allowBackup="true" : 앱이 재설치되어도 db 유지

## Entity
  - @PrimaryKey : 각 항목은 하나 이상의 필드를 기본 키로 정의 해야함
  - @PrimaryKey(autoGenerate = true) : auto_increment 설정
  - @Entity(PrimaryKey = arrayOf("firstName", lastName")) : 두개 이상의 기본키 지정
  - @Entity(tableName = "users") : 테이블 이름을 클래스 이름과 다르게 지정
  - @ColumnInfo(name = "first_name") val firstName: String? : 열이름을 변수명과 다르게 지정
  - @Ignore val picture: Bitmap? : 유지하지 않는 필드
  - @Entity(ignoredColumns = arrayOf("picture")) : 필드를 상속하는 경우 @Entity의 속성의 ignoredColumns 속성을 사용하는 것이 더 쉬움


## DAO (Data Access Object)
  - DAO는 인터페이스 혹은 추상클래스 여야 함
  - @Dao 주석 으로 식별
  - @Insert(onConflict = OnConflictStrategy.IGNORE) : 이미 목록에 있는 것과 동일한 경우 무시
    - https://developer.android.com/reference/androidx/room/OnConflictStrategy.html
  - @Query(SELECT * FROM User WHERE username = :username) fun loadUser(username: String) : List<User>  : 매개변수 이름을 사용하여 일치 시킴
  - @Update fun updateUsers(vararg users: User) : update
  - @Delete fun deleteUsers(vararg users: User) : delete
  - 테이블 조인
  ```
   @Query(
            "SELECT * FROM book " +
            "INNER JOIN loan ON loan.book_id = book.id " +
            "INNER JOIN user ON user.id = loan.user_id " +
            "WHERE user.name LIKE :userName"
        )
        fun findBooksBorrowedByNameSync(userName: String): List<Book>
  ```
  - 
  








# 참고자료
  - https://developer.android.com/training/data-storage/room?hl=ko
  - https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#4
  
