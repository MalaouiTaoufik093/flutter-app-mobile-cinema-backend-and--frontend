import 'package:appmobilecinema/main.dart';
import 'package:flutter/material.dart';
class SettingPage extends StatefulWidget {
  @override
  _SettingPageState createState() => _SettingPageState();
}

class _SettingPageState extends State<SettingPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Setting"),),
      body: Center(
        child: Text("Liste des Setting"),
      ),
    );
  }
}
