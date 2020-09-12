package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter

        binding.rvQuestions.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        for (i in Question.QUERIES.indices) {
            questions.add(Question(Question.QUERIES[i], Question.ANSWERS[i]))
        }

        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                // If swiped left check if the answer of the question is correct at the swiped position.
                if (direction == 4) {
                    if (!questions[position].answer) {
                        questions.removeAt(position)
                        questionAdapter.notifyDataSetChanged()
                    } else {
                        questionAdapter.notifyDataSetChanged()
                        Snackbar.make(
                            rvQuestions,
                            "Question is not answered correctly, question won't be removed",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
                // If swiped right check if the answer of the question is correct at the swiped position.
                else if (direction == 8) {
                    if (questions[position].answer) {
                        questions.removeAt(position)
                        questionAdapter.notifyDataSetChanged()
                    } else {
                        questionAdapter.notifyDataSetChanged()
                        Snackbar.make(
                            rvQuestions,
                            "Question is not answered correctly, question won't be removed",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

            }
        }
        return ItemTouchHelper(callback)
    }

}