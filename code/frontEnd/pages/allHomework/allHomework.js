Page({
  data:{
    time0:"2018-6-10 18:23",
    content0:"1. 搭建一个CI/CD环境，能够让用户在提交一个代码后，系统自动进行CI，通过后自动或者手动进行CD；\n2. 高级要求，能够对你的Application进行监控；\n3. 用一个PPT，对CI/CD环境的搭建做一个详细的介绍，有自己思考更好；\n4. 同时完成一个与PPT相对应的md版本的文档；\n提示：1. 尽量用已有的工具、平台、云服务来完成作业，避免出现较大的工作量。\n2. CI与CD之间可以不考虑自动化，CI之后，手动CD也满足要求；\n\n作业的提交：\n1. 建立一个Repo，将小组成员都加入；\n2. 所有的提交物都存在repo中，提交时请在答案处填写Repo地址。",
    time1:"2018-6-10 20:36",
    content1: "请你积极参与讨论区第一章中的话题，“我们生活在数据库的影子下吗？”，提出你的观点，也可以对其他同学的帖子回复或点赞。\n\n其他话题，可以暂时忽略。\n\nJBoss",
    grade0: "此作业成绩尚未发布",
    grade1: "95"
  },
  toDetail(){
    wx.navigateTo({
      url: '/pages/homework/homework',
    })
  },
  getGrade:function(e){
    if(e.target.dataset.id == "0"){
      wx.showModal({
        title: '成绩',
        content: "此作业成绩尚未发布",
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })  
    }
    else if(e.target.dataset.id == 1){
      wx.showModal({
        title: '成绩',
        content: "95",
        success: function (res) {
          if (res.confirm) {
            console.log('用户点击确定')
          } else if (res.cancel) {
            console.log('用户点击取消')
          }
        }
      })  
    }
  }
})