package my.xbl.com.drawerlayoutdemo;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView left_list;
    private DrawerLayout drawer_layout;
    private String [] str=new String[]{"条目一","条目二","条目三","条目四"};
    private String title;//标题名称
    //左侧滑动的菜单按钮显示
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.main_toll_bar);
        title= (String) getTitle();
        toolbar.setTitle(title);
        //   toolbar.setSubtitle("我是子标题");
        //设置导航栏
        toolbar.setNavigationIcon(R.drawable.menu);
        drawer_layout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        //滑动时图标的转换
        drawerToggle = new ActionBarDrawerToggle(this,
                drawer_layout,
                toolbar,
                R.string.draw_open,
                R.string.draw_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle(title);
                toolbar.setNavigationIcon(R.drawable.back);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle(title);
                toolbar.setNavigationIcon(R.drawable.menu);
            }

        };
        drawer_layout.addDrawerListener(drawerToggle);
        //菜单被点击之后
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (drawer_layout.isDrawerOpen(Gravity.START)){
                    toolbar.setNavigationIcon(R.drawable.back);
                }else {
                    toolbar.setNavigationIcon(R.drawable.menu);
                }
                return false;
            }
        });




        left_list= (ListView) findViewById(R.id.main_left_lv);
        left_list.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_layout_item,
                str
                ));
        //左边菜单被点击的时候，菜单就被收回
        left_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,str[position],Toast.LENGTH_LONG).show();
                //关闭左侧策划菜单
                drawer_layout.closeDrawer(left_list);
                title=str[position];
            }
        });
    }
}
