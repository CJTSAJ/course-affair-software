const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    studentName: '',
    studentId: ''
  },
  
  pick: function () {
    var self = this;
    var openGId = app.globalData.openGId;
    wx.request({
      url: app.globalData.serverUrl + 'pick',
      data: {
        openGId: openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        if (res.statusCode == 200){
          var id = res.data.studentId;
          var name = res.data.studentName;
          self.setData({
            studentName: name,
            studentId: id
          })
        }
        else{
          wx.showModal({
            title: '错误',
            content: "服务器发生错误",
          })
        }
      },
      fail: function (error) {
        wx.showModal({
          title: '错误',
          content: error,
        })
      }
    })
  }
})