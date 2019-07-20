interface PermissionInfo {
  id?:number,
  name:string,
  url?:string,
  method?:string,
  childPermissions?:PermissionInfo[]
}