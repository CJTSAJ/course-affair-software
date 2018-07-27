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
    console.log(openGId)
    wx.request({
      url: 'http://localhost:8080/pick',
      data: {
        openGId: openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        var id = res.data.studentId;
        var name = res.data.studentName;
        self.setData({
          studentName:name,
          studentId:id
        })
      },
      fail: function (error) {

      }
    })
  }
})