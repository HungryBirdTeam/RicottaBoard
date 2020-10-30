<template>
    <div id="EditorMain" class="MoveableBox">
        <div v-if="isHidden">
            <div class="row m-0">
                <v-btn
                    color="blue-grey lighten-4"
                    @click="changeHidden"
                    class="my-auto mx-1"
                    >
                    <v-icon
                        center
                        light
                    >
                        mdi-arrow-up-drop-circle-outline
                    </v-icon>
                </v-btn>
                <div v-if="this.title" class="align-middle my-auto ml-1"> {{ title }} </div>
                <div v-if="!this.title" class="align-middle my-auto ml-1"> 제목 없음 </div>                
            </div>
        </div>
        <div v-if="!isHidden">
            <div class="title row m-0">
                <div class="col-10 p-0 m-0">
                    <div class="semititle row m-0">
                        <v-btn
                        color="blue-grey lighten-4"
                        @click="changeHidden"
                        class="my-auto mx-1"
                        >
                            <v-icon
                                center
                                dark
                            >
                                mdi-arrow-down-drop-circle
                            </v-icon>
                        </v-btn>
                        <input type="text" placeholder="제목" v-model="title" class="ml-1">
                    </div>
                </div>
                <v-btn
                    :loading="isLoading"
                    :disabled="isLoading"
                    color="blue-grey"
                    class="m-auto  white--text"
                    @click="saveEditor"
                    >
                    저장하기
                    <v-icon
                        right
                        dark
                    >
                        mdi-cloud-download
                    </v-icon>
                </v-btn>
            </div>
            <Editor        
                height="500px"
                :initialValue="text"
                ref="toastuiEditor"
                @change = "onEditorChange"
                class="bg-white"
            />
            <input type="text" placeholder="테스트" class="col-12 p-0" v-model="text">
        </div>
    </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
<script src="lodash.js"></script>
<script>
import _ from "lodash"
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/vue-editor';
import { saveAs } from 'file-saver';

export default {
    data() {
        return {
            title: '', 
            text: '',
            isLoading: false,
            isHidden: false,
        }
    },
    components: {
        Editor
    },
    props: {
        editor: Object,
    },
    watch: {
        text: function() {
            this.textChange()
        }
    },
    methods: {
        onEditorChange() {
            var content = this.$refs.toastuiEditor.invoke("getMarkdown");
            this.editor.text = content
            console.log('editor text!', this.editor.text)
        },
        saveEditor() {     
            this.isLoading = true;
            var FileSaver = require ('file-saver');
            var blob = new Blob([this.text], { type : "text / plain; charset = utf-8" });
            var name = "untitle";
            if (this.title) {
                name = this.title
            };
            console.log(name)
            FileSaver.saveAs (blob, name+".md");
            setTimeout(() => (this.isLoading = false), 1000);
        },
        textChange() {
            if (this.text != this.$refs.toastuiEditor.invoke("getMarkdown")) {
                this.$refs.toastuiEditor.invoke("setMarkdown", this.text)
                console.log('text', this.text)
                console.log('editor', this.$refs.toastuiEditor.invoke("getMarkdown"))
            }
        },
        changeHidden() {
            this.isHidden = !this.isHidden
            consol.log(this.isHidden)
        }
    }
}
</script>

<style scoped>
html, body, #EditorMain {
    margin: 0;
    width: 860px;
    height: 610px;
    font-family: 'Helvetica Neue', Arial, sans-serif;
    color: #333;
    background-color: #f6f6f6;
    /* ::v-deep {
        margin: 0;
        font-family: 'Helvetica Neue', Arial, sans-serif;
    } */
}

.title {
    width: 800px;
    height: 50px;
    border: 1px solid #ccc;
    background-color: #f6f6f6;
}

.semititle {
    width: 666.66px;
    height: 50px;
}
</style>