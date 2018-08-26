// pages/allStudent/allStudent.js
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
      url: 'http://localhost:8080/getStudentByGid',
      data: {
        opengid: "tG7EaP4nMFgSbbz8PQ4nOVQkdwScY",
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