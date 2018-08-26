const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: "",
    studentID: "",
    showID:true,
    userInfo: null
  },
  /*userNameInput:function(e)
  {
    this.setData({
      name: e.detail.value
    })
  },
  userIdInput:function(e)
  {
    this.setData({
      studentID: e.detail.value
    })
  },
  nameDone:function(){
    this.setData({
      showName: true
    })
  },
  IdDone:function(){
    this.setData({
      showID: true
    })
  },
  nameModify:function(){
    this.setData({
      showName: false
    })
  },
  idModify:function(){
    this.setData({
      showID: false
    })
  },*/
  onShow: function(){
    var self = this;
    wx.request({
      url: 'http://localhost:8080/getNameAndSno',
      data: {
        opengid: app.globalData.openGId,
        openid: app.globalData.openId,
        identity: app.globalData.identity,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res.data);
        self.setData({
          name: res.data.name,
          studentID: res.data.sno
        })
      }
    })
    console.log(app.globalData.identity)
    if(app.globalData.identity != "student"){
      self.setData({
        showID: false
      })
    }
  },
  onLoad: function(){
    this.setData({
      userInfo: app.globalData.userInfo
    })
  },
  save:function(e){
    console.log(e.detail.value.username);
    console.log(e.detail.value.studentid);
    wx.request({
      url: 'http://localhost:8080/modifyInformation',
      data: {
        opengid: app.globalData.openGId,
        openid: app.globalData.openId,
        identity: app.globalData.identity,
        name: e.detail.value.username,
        sno: e.detail.value.studentid
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function(res){
        wx.showToast({
          title: '成功',
          icon: 'success',
          mask: false,
          success: function () {
            console.log("111");
          },
          complete: function () {
            console.log("kkk");
          }
        })
      },
      fail: function(){
        wx.showToast({
          title: '服务器错误',
          icon: 'loading',
          mask: false,
          success:function(){

          }
        })
      }
    })
    
  }
})