<template>
  <div class="comments-container">
    <van-skeleton title row="6" :loading="loading">
      <div class="vcomments">
        <div class="vpanel">
          <div class="vwrap">
            <div class="vheader item3">
              <input name="nick" placeholder="昵称" class="vnick vinput" type="text">
              <input name="mail" placeholder="邮箱" class="vmail vinput" type="email">
              <input name="link" placeholder="网址(http://)" class="vlink vinput" type="text">
            </div>
            <div class="vedit">
              <textarea id="veditor" class="veditor vinput"></textarea>
            </div>
            <div class="vrow">
              <div class="vcol vcol-30"></div>
              <div class="vcol vcol-70 text-right">
                <button type="button" class="vsubmit vbtn">提交</button>
              </div>
            </div>
          </div>
        </div>
        <div class="vcount" style="display: block;">
          <span class="vnum">{{ list.length }}</span>&nbsp;评论
        </div>
        <div class="vcards">
          <div class="vcard" v-for="i in list" :key="i.id">
            <img class="vimg" src="https://gravatar.loli.net/avatar/c4ca4238a0b923820dcc509a6f75849b?d=mp&amp;v=1.4.14">
            <div class="vh">
              <div class="vhead">
                <a class="vnick" rel="nofollow" href="http://1" target="_blank">{{ i.name }}</a>
              </div>
              <div class="vmeta"><span class="vtime">{{ i.createTime }}</span></div>
              <div class="vcontent"><p>{{ i.content }}</p></div>
            </div>
          </div>
        </div>
      </div>
    </van-skeleton>
  </div>
</template>

<script>

import {commentFilterList} from "@/api/comment"

export default {
  name: "comments",
  props: ['id'],
  data() {
    return {
      list: {},
      loading: true
    }
  },
  methods: {
    fetchData(id) {
      commentFilterList({articleId: id}).then(res => {
        this.list = res.data
      })
    }
  }
}
</script>

<style scoped>
.comments-container {
  position: relative;
  z-index: 1;
  color: #5f5f5f;
  background-color: #f7f7f7;
}

button, input, select, textarea {
  font-family: -apple-system, SF UI Text, Arial, PingFang SC, Hiragino Sans GB, Microsoft YaHei, WenQuanYi Micro Hei, sans-serif;
  font-size: 13px;
  line-height: 1.6;
  resize: none;
}

.vcomments {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
}

.vwrap {
  border: 1px solid #f0f0f0;
  border-radius: 4px;
  margin-bottom: 10px;
  overflow: hidden;
  position: relative;
  padding: 10px;
  border: none !important;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 0, 0, .04);
}

.vwrap .vheader .vinput {
  width: 100%;
  border-bottom: 1px dashed #dedede;
  resize: none;
  outline: none;
  padding: 10px 5px;
  max-width: 100%;
  font-size: .775em;
}

.vwrap .vedit {
  position: relative;
  padding-top: 10px;
}

.veditor {
  width: 100%;
  min-height: 8.75em;
  font-size: .875em;
  background: transparent;
  resize: vertical;
  -webkit-transition: all .25s ease;
  transition: all .25s ease;
}

textarea {
  font-family: -apple-system, SF UI Text, Arial, PingFang SC, Hiragino Sans GB, Microsoft YaHei, WenQuanYi Micro Hei, sans-serif;
  font-size: 13px;
  line-height: 1.6;
  resize: none;
}

.vrow {
  font-size: 0;
  padding: 10px 0;
}

.vrow .vcol {
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
}

.vrow .vcol.vcol-30 {
  width: 30%;
}

.vrow .vcol.vcol-70 {
  width: 70%;
}

.text-right {
  text-align: right;
}

.vbtn {
  -webkit-transition-duration: .4s;
  transition-duration: .4s;
  text-align: center;
  color: #555;
  border: 1px solid #ededed;
  border-radius: .3em;
  display: inline-block;
  background: transparent;
  margin-bottom: 0;
  font-weight: 400;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  white-space: nowrap;
  padding: .5em 1.25em;
  font-size: .875em;
  line-height: 1.42857143;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  outline: none;
}

.vcount {
  padding: 5px;
  font-weight: 600;
  font-size: 1.25em;
}

.vcount .vnum {
  padding-right: 6px;
}

.vcards {
  width: 100%;
}

.vcards .vcard {
  padding-top: 1.25em;
  position: relative;
  display: block;
}

.vcards .vcard .vimg {
  width: 2.5em;
  height: 2.5em;
  float: left;
  border-radius: 50%;
  margin-right: .7525em;
  border: 1px solid #f5f5f5;
}

.vcards .vcard:last-child .vh {
  border-bottom: none;
}

.vcards .vcard .vh {
  overflow: hidden;
  padding-bottom: .5em;
  border-bottom: 1px dashed #f5f5f5;
}

.vcards .vcard .vhead {
  line-height: 1.5;
  margin-top: 0;
}

.vcards .vcard .vhead .vnick {
  position: relative;
  font-size: .875em;
  font-weight: 500;
  margin-right: .875em;
  cursor: pointer;
  text-decoration: none;
  display: inline-block;
}

.vcards .vcard .vh .vmeta {
  line-height: 1;
  position: relative;
}

.vcards .vcard .vh .vtime {
  font-size: .75em;
  margin-right: .875em;
  color: #b3b3b3;
}

.vcontent {
  word-wrap: break-word;
  word-break: break-all;
  font-size: .875em;
  line-height: 2;
  position: relative;
  margin-bottom: .75em;
  padding-top: .625em;
}
.vcontent view {
  margin: 0px !important;
}
</style>
