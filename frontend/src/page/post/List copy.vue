<template>
    <div class="post">
        <div class="wrapB">
            <h2>전체글</h2>

            <section class="post-list">
                <div>
                    <div class="post-card">
                        <a>
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    제목
                                </h3>
                                <p class="content">컨텐츠</p>
                                <span class="date">2020-06-19ㆍ</span>  
                                <span class="comment">댓글 0개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                닉네임
                            </a>
                            <span>
                                ♥ 2
                            </span>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="post-card">
                        <a>
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    제목
                                </h3>
                                <p class="content">컨텐츠</p>
                                <span class="date">2020-06-19ㆍ</span>  
                                <span class="comment">댓글 0개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                닉네임
                            </a>
                            <span>
                                ♥ 2
                            </span>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="post-card">
                        <a>
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    제목
                                </h3>
                                <p class="content">컨텐츠</p>
                                <span class="date">2020-06-19ㆍ</span>  
                                <span class="comment">댓글 0개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                닉네임
                            </a>
                            <span>
                                ♥ 2
                            </span>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="post-card">
                        <a>
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    제목
                                </h3>
                                <p class="content">컨텐츠</p>
                                <span class="date">2020-06-19ㆍ</span>  
                                <span class="comment">댓글 0개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                닉네임
                            </a>
                            <span>
                                ♥ 2
                            </span>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="post-card">
                        <a>
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    제목
                                </h3>
                                <p class="content">컨텐츠</p>
                                <span class="date">2020-06-19ㆍ</span>  
                                <span class="comment">댓글 0개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                닉네임
                            </a>
                            <span>
                                ♥ 2
                            </span>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="post-card">
                        <a>
                            <div :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}" class="post-img"/>
                            
                            <div class="contents">
                                <h3>
                                    제목
                                </h3>
                                <p class="content">컨텐츠</p>
                                <span class="date">2020-06-19ㆍ</span>  
                                <span class="comment">댓글 0개</span>
                                
                            </div>
                        </a>

                        <div class="writer-wrap">
                            <a>
                                닉네임
                            </a>
                            <span>
                                ♥ 2
                            </span>
                        </div>
                    </div>
                </div>
                
            </section>

            <!-- <div class="tag-list-wrap">
                <h4>Test Link</h4>
                <ul class="tag-list">
                    <li>
                        <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn--text">
                          회원 가입 페이지
                        </router-link> 
                    </li>
                    <li>
                        <router-link v-bind:to="{name:constants.URL_TYPE.USER.MYPAGE}" class="btn--text">
                          회원 관리 페이지
                        </router-link> 
                    </li>
                    <li>
                        <router-link v-bind:to="{name:constants.URL_TYPE.POST.TEST_BOARD}" class="btn--text">
                         Vue-Moveable 테스트 페이지
                        </router-link> 
                    </li>

                </ul>   
            </div> -->
        </div>
        

    </div>
</template>
 
<script>
    import '../../assets/css/post.scss'
    import constants from '../../lib/constants'

    export default {
        name:"Post",
        components:{
        },
        watch: {
        },
        created() {
        },
        methods: {
        },
        data: () => {
            return {
                constants
            }
        }
    }
</script>