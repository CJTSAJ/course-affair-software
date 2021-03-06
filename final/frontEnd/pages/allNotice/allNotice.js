const app = getApp()
Page({
  data:{
    allContent: [],
    userInfo: null,
    isTeacher: false
  },
  onPullDownRefresh: function () {
    wx.showNavigationBarLoading()
    this.onShow()
  },
  toDetail:function(){
    wx.navigateTo({
      url: '/pages/notice/notice',
    })
  },
  toEdit:function(){
    wx.navigateTo({
      url: '/pages/addNotice/addNotice',
    })
  },
  onShow: function(){
    this.setData({
      userInfo: app.globalData.userInfo
    })
    if(app.globalData.identity != "student"){
      this.setData({
        isTeacher: true
      })
    }
    var self = this;
    var opengid = app.globalData.openGId;
    console.log("openid:" + opengid);
    wx.request({
      url: app.globalData.serverUrl + 'hibernate/getNotice',
      data: app.globalData.openGId,
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        if(res.statusCode == 200){
          console.log("success:" + res.data);
          console.log("content::" + res.data[0])
          self.setData({
            allContent: res.data
          })
        }else{
          wx.showModal({
            title: '错误',
            content: '服务器发生错误',
          })
        }
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh()
      },
      fail: function (error) {
        console.log("error:" + error);
      }
    })
  }
})