package com.daerong.myapplication

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import com.daerong.myapplication.ui.theme.MyApplicationTheme

@Composable
fun ConstraintLayoutContent(){
    ConstraintLayout {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text(text = "Button 1")
        }

        Text("Text", Modifier.constrainAs(text){
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }
    }
}

@Composable
fun DecoupledConstraintLayout(){
    BoxWithConstraints {
        val constrains = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp)
        }else{
            decoupledConstraints(margin = 32.dp)
        }

        ConstraintLayout(constrains) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button")
                ) {
                Text(text = "Button")
            }
            Text("Text",Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin : Dp) : ConstraintSet{
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button){
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text){
            top.linkTo(button.bottom, margin = margin)
        }

    }
}

@Composable
fun LargeConstraintLayout(){
    ConstraintLayout {
        val text = createRef()
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(text = "This is very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width = Dimension.preferredWrapContent.atLeast(100.dp)
            }
        )
    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview3(){
    MyApplicationTheme {
        DecoupledConstraintLayout()
    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview2(){
    MyApplicationTheme {
        LargeConstraintLayout()
    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview(){
    ConstraintLayoutContent()
}