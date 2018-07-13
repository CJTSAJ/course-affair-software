const app = getApp();
// pages/addNotice/addNotice.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    content: '',
    openid: null
  },

  cancel:function(){
    wx.showModal({
      title: '提示',
      content: "是否放弃本次编辑？",
      success: function (res) {
        if (res.confirm) {
          console.log('用户点击确定')
          wx.redirectTo({
            url: '/pages/allNotice/allNotice',
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  getContent:function(e){
    var c = e.detail.value;
    console.log(c)
    this.setData({
      content:c
    })
  },
  confirm:function(){
    var id = app.globalData.openId;
    var openGId = app.globalData.openGId;
    var content = this.data.content;
    console.log(id)
    console.log(content)
    console.log(openGId)
    var tempData = {
      openid: id,
      content: content
    }
    wx.request({
      url: 'http://127.0.0.1:8080/hibernate/addNotice',
      data: {
        openid: id,
        content: content,
        openGId: openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        wx.navigateBack({
          delta: 1
        })
      },
      fail: function (error) {
        
      }
    })
  }
})