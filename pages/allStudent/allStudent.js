// pages/allStudent/allStudent.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allStudent: []
  },

  onShow: function(){
    var self = this;
    wx.request({
      url: app.globalData.serverUrl + 'getStudentByGid',
      data: {
        opengid: app.globalData.openGId,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res);
        self.setData({
          allStudent: res.data
        })
      }
    })
  }
})