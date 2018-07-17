Page({

  /**
   * 页面的初始数据
   */
  data: {
    informationData0:"老师发布了作业",
    time0:"2018-6-10 18:23",
    content0: "1. 搭建一个CI/CD环境，能够让用户在提交一个代码后，系统自动进行CI，通过后自动或者手动进行CD；\n2. 高级要求，能够对你的Application进行监控；\n3. 用一个PPT，对CI/CD环境的搭建做一个详细的介绍，有自己思考更好；\n4. 同时完成一个与PPT相对应的md版本的文档；\n提示：1. 尽量用已有的工具、平台、云服务来完成作业，避免出现较大的工作量。\n2. CI与CD之间可以不考虑自动化，CI之后，手动CD也满足要求；\n\n作业的提交：\n1. 建立一个Repo，将小组成员都加入；\n2. 所有的提交物都存在repo中，提交时请在答案处填写Repo地址。",
    informationData1: "老师发布了公告",
    time1: "2018-6-10 19:17",
    content1: "由于我要参加科教文组织举办的一个在线教育相关会议并作交流，经向学校教务处申请，并获得批准，原定6月12日上午3-4节的数据库课程，调整至6月15日上午1-2节，上课地点仍在上院315。\n\n请相互转告。\n\n对由此给你带来的不便，我表示歉意。"
  },
  toDetail:function(){
    wx.navigateTo({
      url: '/pages/homework/homework',
    })
  },
  toDetail1: function () {
    wx.navigateTo({
      url: '/pages/notice/notice',
    })
  },
  confirm:function(){
    this.setData({
      showModal:true
    })
  },
  cancel: function () {
    this.setData({
      showModal: false
    })
  },
  toSetting:function(){
    wx.redirectTo({
      url: '/pages/managerSetting/mangerSetting',
    })
  }
})