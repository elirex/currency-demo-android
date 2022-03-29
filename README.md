# Currency demo App
App's architecture is according to [Android app architecture guide](https://developer.android.com/jetpack/guide) to implement.
## Architecture description
### UI layer
* DeomActivity provides two buttons, one is to load data, another is to sort the data.
* CurrencyListFragment to show the data.
* DemoViewModel is a state holder that holds data, exposes it to UI, and handles the domain layer.
### Domain layer
* LoadCurrencyInfoUseCase handles the repository to load data.
* SortCurrencyInfoUseCase sorts the data.
### Data layer
* CurrencyRepository handles the data source.
*  LocalDataSource actually interacts with the database.
## Dependency
* [Hilt](https://dagger.dev/hilt/) - The dependency injection framework.
* [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - The lifecycle-aware library to help to build the lifecycle-aware between ViewModel, Activity, Fragment.
* [Room](https://developer.android.com/training/data-storage/room) - The database libarary that provides an abstraction layer over SQLite.
* [Mockk](https://mockk.io/) - The mock object library to help the unit testing.
## Other
If you want to modify the default database, you can reference [scripts](https://github.com/elirex/currency-demo-android/tree/master/scripts) folder.
## License
```
Copyright 2022 Sheng-Yuan, Wang (Elirex)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
