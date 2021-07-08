# Docker-compose 一鍵啟動簡易架構範本

## 技術選情

- Springboot 後端
    - checkstyle, pmd, spotbugs, jacoco
    - mybatis plus
    - actuator
    - lombok
    - swagger
    - junit
- 前端 (TBD)
- MySQL 資料庫
- Docker
- Github Action (CI)

## Github 設定

- 設定 [GITHUB_TOKEN](https://docs.github.com/en/actions/reference/authentication-in-a-workflow)
  | [中文](https://blog.csdn.net/ht370671963/article/details/111995883)
    - 基本 repo 操作與 workflow 必選
- Docker 發佈觸發機制為新增 Release/Tag, 建立之後 Github Action 會自動執行, Image 存放在 Packages.

## 運行需求 & 指令

* 安裝 [Docker](https://docs.docker.com/get-docker/) | [中文](https://www.runoob.com/docker/ubuntu-docker-install.html)
* 安裝 [Docker-compose](https://docs.docker.com/compose/install/)
  | [中文](https://www.runoob.com/docker/docker-compose.html)
* 在本資料夾路徑執行 docker-compose up -d
* 頁面路徑
    - http://docker.domain/
* 後端 API 文件
    - http://docker.domain:8080/doc.html
* 關閉指令為 docker-compose down
* 如需完全清除, 指令為 docker-compose down -v

## 開發測試需求 & 指令

* 安裝 [JDK11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
  | [中文](https://www.runoob.com/java/java-environment-setup.html)
    - PS. 因版權問題, 不一定非要 Oracle 版本 JDK,
      建議使用 [Amazon Corretto](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
* 安裝 [Docker](https://docs.docker.com/get-docker/) | [中文](https://www.runoob.com/docker/ubuntu-docker-install.html)
* 安裝 [Docker-compose](https://docs.docker.com/compose/install/)
  | [中文](https://www.runoob.com/docker/docker-compose.html)
* 執行 gradle docker (如果使用 IntelliJ, 有 Gradle Panel 可以直接點選 Tasks>distribution>docker)
* 執行 docker-compose -f develop-compose.yml build
* 執行 docker-compose -f develop-compose.yml up -d
* 關閉指令為 docker-compose -f develop-compose.yml down
* 如需完全清除, 指令為 docker-compose -f develop-compose.yml down -v