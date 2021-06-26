import 'package:appmobilecinema/main.dart';
import 'package:appmobilecinema/menuitem.dart';
import 'package:appmobilecinema/settingpage.dart';
import 'package:appmobilecinema/villepage.dart';
import 'package:flutter/material.dart';

void main() => runApp(MaterialApp(
      theme: ThemeData(appBarTheme: AppBarTheme(color: Colors.orange)),
      home: MyApp(),
    ));

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  final menus = [
    {"title": "Home", "icon": Icon(Icons.home), "page": VillePage()},
    {"title": "Setting", "icon": Icon(Icons.settings), "page": SettingPage()},
    {"title": "Contact", "icon": Icon(Icons.contact_mail), "page": SettingPage()},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Cinema Page"),
      ),
      body: Center(
        child: Text("Home Cinema...."),
      ),
      drawer: Drawer(
        child: ListView(
          children: <Widget>[
            DrawerHeader(
              child: Center(
                child: CircleAvatar(
                  backgroundImage: AssetImage("./images/profile.png"),
                  radius: 50,
                ),
              ),
              decoration: BoxDecoration(
                  gradient:
                      LinearGradient(colors: [Colors.white, Colors.orange])),
            ),
            ...this.menus.map((item) {
              return new Column(
                children: <Widget>[
                  Divider(color: Colors.orange),
                  MenuItem(item['title'],item['icon'], (context) {
                    Navigator.pop(context);
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) => item['page']));
                  })
                ],
              );
            })
          ],
        ),
      ),
    );
  }
}
