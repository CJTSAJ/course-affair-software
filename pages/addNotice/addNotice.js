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
  confirm:function(e){
    var id = app.globalData.openId;
    var openGId = app.globalData.openGId;
    var content = e.detail.value.textarea
    console.log(id)
    console.log(content)
    console.log(openGId)
    if(content.length != 0){
      wx.request({
        url: 'http://207.148.114.118:8080/courseAffair/hibernate/addNotice',
        data: {
          openid: id,
          content: content,
          openGId: openGId
        },
        method: 'POST',
        header: { 'content-type': 'application/json' },
        success: function (res) {
          if(res.statusCode == 200){
            wx.showToast({
              title: '成功',
              icon: 'success',
              mask: false,
              success:function(){
                setTimeout(function(){
                  wx.navigateBack({
                    delta: 1
                  })
                }, 1500)
              }
            })
            
          }else{
            wx.showModal({
              title: '错误',
              content: '服务器发生错误',
            })
          }
        },
        fail: function (error) {
        }
      })
    }else{
      wx.showModal({
        title: '提示',
        content: '公告内容不能为空',
      })
    }
  }
})