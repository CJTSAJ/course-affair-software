Page({

  /**
   * 页面的初始数据
   */
  data: {
    name: "XXX",
    studentID: 516030910000,
    showName: true,
    showID:true
  },
  userNameInput:function(e)
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
  }
})