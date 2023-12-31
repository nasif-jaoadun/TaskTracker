package com.hishabee.tasktracker

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.hishabee.tasktracker.databinding.ActivityMainBinding
import com.hishabee.tasktracker.viewmodel.TaskListingsViewModal

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var taskViewModal: TaskListingsViewModal
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationIcon(R.drawable.icon_actionbar_menu)
        binding.contentMain.swipeLayout.setOnRefreshListener {
            taskViewModal.refreshData()
        }
        taskViewModal= ViewModelProvider(this).get(TaskListingsViewModal::class.java)
        taskViewModal.taskData.observe(this, Observer {
            val adapter = TaskRecyclerViewAdapter(this, it)
            binding.contentMain.recyclerViewTaskList.adapter = adapter
            binding.contentMain.swipeLayout.isRefreshing = false
        })


        binding.fab.setOnClickListener { view ->
            binding.contentMain.clNewTask.visibility = View.VISIBLE
            binding.contentMain.recyclerViewTaskList.visibility = View.GONE
            binding.fab.isClickable = false
        }

        binding.contentMain.ivAddTask.setOnClickListener{
//            taskViewModal.addTask()
            if(binding.contentMain.etTaskName.text.toString().trim().isEmpty()){
                Toast.makeText(this, getString(R.string.error_empty_task), Toast.LENGTH_LONG).show()
            }else{
                resetUI()
            }

        }

        binding.contentMain.ivCancelAddTask.setOnClickListener{
            resetUI()
        }


    }

    private fun resetUI() {
        binding.contentMain.etTaskName.text.clear()
        binding.contentMain.clNewTask.visibility = View.GONE
        binding.contentMain.recyclerViewTaskList.visibility = View.VISIBLE
        binding.fab.isClickable = true
    }


    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}