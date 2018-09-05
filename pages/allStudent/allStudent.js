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
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: app.globalData.serverUrl + 'getStudentByGid',
      data: {
        opengid: app.globalData.openGId,
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
        console.log(res);
        if(res.statusCode == 200){
          self.setData({
            allStudent: res.data
          })
        }else{
          wx.showModal({
            title: '错误',
            content: '服务器发生错误',
          })
        }
        
        wx.hideLoading();
      }
    })
  }
})