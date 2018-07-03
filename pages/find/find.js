Page({
  data: {
    showModal: false
  },
  toPerson:function(){
    wx.navigateTo({
      url: '/pages/person/person',
    })
  },
  sign:function(){
    this.setData({
      showModal: true
    })
  },
  hideModal:function(){
    this.setData({
      showModal: false
    })
  },
  onCancel:function(){
    this.hideModal();
  },
  onConfirm:function(){
    this.hideModal();
  },
  toNotice:function(){
    wx.navigateTo({
      url: '/pages/allNotice/allNotice',
    })
  },
  toHomework:function(){
    wx.navigateTo({
      url: '/pages/allHomework/allHomework',
    })
  }
})