import 'package:appmobilecinema/GlobalVariables.dart';
import 'package:appmobilecinema/main.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:appmobilecinema/CinemasPage.dart';

class VillePage extends StatefulWidget {
  @override
  _VillePageState createState() => _VillePageState();
}

class _VillePageState extends State<VillePage> {
  List<dynamic> listVilles;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Villes"),),
      body: Center(
        child: this.listVilles==null?CircularProgressIndicator():
            ListView.builder(
                itemCount: (this.listVilles==null)?0:this.listVilles.length,
                itemBuilder: (context,index){
                  return Card(
                    color: Colors.orange,
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: RaisedButton(
                        color: Colors.white,
                        child: Text(this.listVilles[index]["name"],
                        style: TextStyle(color: Colors.black),
                        ),
                        onPressed: (){
                          Navigator.push(context, MaterialPageRoute(
                            builder: (context)=>new CinemasPage(this.listVilles[index])
                          ));
                        },
                      ),
                    ),
                  );
                }
            )
      ),
    );
  }
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    loadVvilles();
  }

  void loadVvilles() {
    String url=GlobalData.host+"/villes";
        //"http://192.168.1.103:9092/villes";
    http.get(url)
    .then((resp){
       setState(() {
         this.listVilles=json.decode(resp.body)["_embedded"]["villes"];
       });
    }).catchError((err){
      print(err);
    });
  }
}
