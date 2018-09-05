const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    allStudent:[],
    studentName: '',
    studentId: '',
  },
  onShow: function(){
    var self = this;
    wx.request({
      url: app.globalData.serverUrl + 'getStudentByGid',
      data:{
        opengid: app.globalData.openGId
      },
      method: 'POST',
      header: { 'content-type': 'application/json' },
      success:function(res){
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
      }
    })
  },

  newPick: function(){
    var self = this;
    var rand = parseInt(Math.random()*self.data.allStudent.length);
    console.log(rand);
    this.setData({
      studentName: self.data.allStudent[rand].studentName,
      studentId: self.data.allStudent[rand].studentid
    })
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