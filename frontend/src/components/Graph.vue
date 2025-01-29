<script setup>
import { errorStatus } from '@/graphState';
</script>

<template>
    <canvas id="graph" width="400" height="400" ></canvas>
    <div v-if="errorStatus">
        <p>{{ errorStatus }}</p>
    </div>
    <button @click="getDots">Show All Dots</button>
</template>

<script>
import { eventBus } from '@/eventBus';
import { graphResponse, allDotsJson } from '@/graphState';
import { userLogin } from '@/authState';
    export default {
        mounted() {
            eventBus.value.triggerFunction = this.wrapper;
        },

        beforeUnmount() {
            eventBus.value.triggerFunction = null;
        },

        methods: {
            wrapper() {
                const x = graphResponse.value.x;
                const y = graphResponse.value.y;
                const r = graphResponse.value.r;

                const inArea = graphResponse.value.inArea;

                if (inArea.startsWith("Error")) {
                    console.log(inArea);
                    errorStatus.value = inArea.substring(7) + ", value must be int in correct range!";
                } else {
                    errorStatus.value = "";
                    this.drawGraph(x, y, r, inArea);
                }
            },

            drawGraph(x, y, r, inArea) {
                const display = document.getElementById("graph");
                const canvas = display.getContext("2d");

                display.style.cssText = "background-color: bisque";

                const W = 400
                const H = 400

                const scale = (W-20)/10;

                x*=scale
                y*=scale
                r*=scale

                canvas.clearRect(0, 0, W, H);                


                // rect drawing
                canvas.beginPath()
                canvas.fillStyle = "cadetblue"
                canvas.fillRect(W/2 - r, H/2, r, r/2);
                canvas.fill()

                // triangle drawing
                canvas.beginPath()

                canvas.moveTo(W/2, H/2);
                canvas.lineTo(W/2+r, H/2);
                canvas.lineTo(W/2, H/2+r/2);
                
                canvas.fill();

                // circle drawing
                canvas.beginPath()

                canvas.moveTo(W/2, H/2);
                canvas.arc(W/2, H/2, r, Math.PI/180, (Math.PI/180)*270, true);

                canvas.fill();

                // arrows drawing
                canvas.beginPath()

                canvas.moveTo(0, H/2);
                canvas.lineTo(W, H/2);
                canvas.moveTo(W/2, 0);
                canvas.lineTo(W/2, H);

                canvas.stroke()


                // label drawing
                canvas.beginPath()
                canvas.fillStyle = "black"
                canvas.font = "7px Verdana";  

                let textCounter = -5;

                for (let i = 10; i <= W-10; i += scale) {
                    canvas.fillRect(i, H/2-2.5, 2, 5)
                    canvas.fillText(textCounter, i-5, H/2+15);

                    textCounter++;
                }


                textCounter = 5;

                for (let j = 10; j <= H-10; j += scale) {
                    if (textCounter != 0) {
                        canvas.fillRect(W/2 - 2.5, j, 5, 2);
                        canvas.fillText(10 - textCounter, W/2-10, j+5, 10);
                    }

                    textCounter++;
                }
            

                // dot drawing
                canvas.beginPath()
                if (inArea == "in Area!") {
                    canvas.fillStyle = "green";                    
                } else {
                    canvas.fillStyle = "red"
                    console.log(inArea);
                }

                canvas.arc(W/2 + x, H/2 - y, 10, 0, 360);
                canvas.fill()

            },

            async getDots() {
                const response = await fetch(`http://localhost:8081/graph/dots?username=${userLogin.value}`, {
                    method: "GET"
                });

                allDotsJson.value = await response.json();
                
            },
        }
    }
</script>

<style scoped>
    #graph {
        display: flex;
        position: absolute;
        align-self: center;
        margin: 0;

        right: 0;
        top: 300px;
    }

    p {
        color: brown
    }
</style>

